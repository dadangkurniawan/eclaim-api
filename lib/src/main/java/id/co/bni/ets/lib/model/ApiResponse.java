package id.co.bni.ets.lib.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * API response wrapper.
 *
 * @param <T> type of object response
 *
 * @author Raffi Ditya
 * @see ResponseEntity
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ApiResponse<T> extends ResponseEntity<ApiResponse.ApiWrapper<T>> {

    public ApiResponse(ApiWrapper<T> body, HttpStatus status) {
        super(body, status);
    }

    /**
     * Returns spring's HTTP ok {@code ResponseEntity} object with given both content and message.
     *
     * @param content entity's object to be sent.
     * @param message string message.
     * @param <T>     type of object entity.
     *
     * @return {@code ResponseEntity} contains both content and string message with ok status header.
     */
    public static <T> ApiResponse<T> apiOk(T content, String message) {
        return new ApiResponse<>(new ApiWrapper<>(content, message), HttpStatus.OK);
    }

    /**
     * Returns spring's HTTP ok {@code ResponseEntity} object with given content.
     *
     * @param content entity's object to be sent.
     * @param <T>     type of object entity.
     *
     * @return {@code ResponseEntity} contains object entity content with ok status header.
     */
    public static <T> ApiResponse<T> apiOk(T content) {
        return apiOk(content, null);
    }

    /**
     * Returns spring's HTTP ok {@code ResponseEntity} object with given message.
     *
     * @param message string message.
     *
     * @return {@code ResponseEntity} contains string message with ok status header.
     */
    public static <T> ApiResponse<T> apiOk(String message) {
        return apiOk(null, message);
    }

    @Override
    public String toString() {
        if (getBody() != null) {
            return this.getBody()
                    .toString();
        }

        return "{}";
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApiWrapper<T> {

        private T content;

        private String message;

        private ApiWrapper(T content, String message) {
            this.content = content;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getContent() {
            return content;
        }
    }
}
