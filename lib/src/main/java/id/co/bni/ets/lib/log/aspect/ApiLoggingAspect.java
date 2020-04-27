package id.co.bni.ets.lib.log.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import id.co.bni.ets.lib.log.model.entity.ApiLog;
import id.co.bni.ets.lib.log.model.entity.ApiLogId;
import id.co.bni.ets.lib.log.repo.ApiLogRepository;
import id.co.bni.ets.lib.util.UserIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ApiLoggingAspect {

    private final ObjectWriter writer;
    private final ApiLogRepository apiLogRepository;

    public ApiLoggingAspect(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
        this.writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
    }

    @Pointcut("@annotation(id.co.bni.ets.lib.log.annotation.LogApi)")
    public void annotationPointcut() {
        // Empty method to register pointcut.
    }

    @Pointcut("execution(* *(.., @org.springframework.web.bind.annotation.RequestBody (*), ..))")
    public void annotationRequestBodyPointcut() {
        // Empty method to register pointcut.
    }

    @AfterReturning(pointcut = "annotationPointcut() && !annotationRequestBodyPointcut()", returning = "result")
    public void afterReturning(Object result) {
        ApiLog apiLog = logResponse(result);
        if (result instanceof ResponseEntity) {
            apiLog.setStatusCode(((ResponseEntity<?>) result).getStatusCodeValue());
        }
        apiLogRepository.save(apiLog);
    }

    @AfterThrowing(pointcut = "annotationPointcut() && !annotationRequestBodyPointcut()", throwing = "ex")
    public void afterThrowing(ResponseStatusException ex) {
        ResponseEntity<String> responseEntity = ResponseEntity
                .status(ex.getStatus())
                .body(ex.getReason());
        ApiLog apiLog = logResponse(responseEntity);
        apiLog.setStatusCode(responseEntity.getStatusCodeValue());
        apiLogRepository.save(apiLog);
    }

    @Around(value = "annotationPointcut() && annotationRequestBodyPointcut() ")
    public Object aroundPostPutDelete(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Annotation[][] methodAnnotations = method.getParameterAnnotations();
        int parameterIndex = -1;
        for (int i = 0; i < methodAnnotations.length; i++) {
            Annotation[] annotations = methodAnnotations[i];
            for (Annotation annotation : annotations) {
                if (RequestBody.class.isAssignableFrom(annotation.getClass())) {
                    parameterIndex = i;
                    break;
                }
            }
            if (parameterIndex != -1) {
                break;
            }
        }

        String request = parameterIndex != -1 && parameterIndex < pjp.getArgs().length
                         ? writer.writeValueAsString(pjp.getArgs()[parameterIndex])
                         : null;

        try {
            Object result = pjp.proceed();
            ApiLog apiLog = logResponse(result);
            apiLog.setRequest(request);

            if (result instanceof ResponseEntity) {
                apiLog.setStatusCode(((ResponseEntity<?>) result).getStatusCodeValue());
            }

            apiLogRepository.save(apiLog);

            return result;
        } catch (Throwable throwable) {
            ResponseEntity<String> responseEntity = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
            if (throwable instanceof ResponseStatusException) {
                ResponseStatusException originalException = (ResponseStatusException) throwable;
                responseEntity = ResponseEntity
                        .status(originalException.getStatus())
                        .body(originalException.getReason());
            } else if (throwable instanceof NestedRuntimeException) {
                NestedRuntimeException originalException = (NestedRuntimeException) throwable;
                Throwable mostThrowable = originalException.getMostSpecificCause();
                responseEntity = ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(mostThrowable.getMessage());
            }

            ApiLog apiLog = logResponse(responseEntity);
            apiLog.setRequest(request);
            apiLog.setStatusCode(responseEntity.getStatusCodeValue());
            apiLogRepository.save(apiLog);

            throw throwable;
        }
    }

    private ApiLog logResponse(Object result) {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
                .getAuthentication();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        ApiLogId apiLogId = new ApiLogId();
        apiLogId.setUserId(UserIdUtil.getUserId(authentication));
        ApiLog apiLog = new ApiLog();
        apiLog.setApiLogId(apiLogId);
        apiLog.setUrl(request.getRequestURI());
        apiLog.setMethod(request.getMethod());

        if (result instanceof Throwable) {
            apiLog.setResponse(((Throwable) result).getMessage());
        } else {
            try {
                String prettyResponse = writer.writeValueAsString(result);
                apiLog.setResponse(prettyResponse);
            } catch (JsonProcessingException ex) {
                apiLog.setResponse(result.toString());
            }
        }

        return apiLog;
    }
}
