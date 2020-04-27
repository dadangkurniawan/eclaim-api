package id.co.bni.ets.lib.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Logging Api request and response on method controller to {@code ApiLoggingAspect}.
 *
 * @author Raffi Ditya
 * @see id.co.bni.ets.lib.log.aspect.ApiLoggingAspect
 * @since 1.0.0.RELEASE
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogApi {
    // Empty annotation.
}
