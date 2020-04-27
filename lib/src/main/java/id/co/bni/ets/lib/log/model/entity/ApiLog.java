package id.co.bni.ets.lib.log.model.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_api_log", schema = "dbo")
public class ApiLog {

    @JsonUnwrapped
    @EmbeddedId
    private ApiLogId apiLogId;

    private String url;

    private Integer statusCode;

    private String method;

    private String request;

    private String response;

    public ApiLogId getApiLogId() {
        return apiLogId;
    }

    public void setApiLogId(ApiLogId apiLogId) {
        this.apiLogId = apiLogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @PrePersist
    private void preCreate() {
        if (getApiLogId() != null) {
            getApiLogId().setDate(new Date());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiLog)) return false;
        ApiLog apiLog = (ApiLog) o;
        return Objects.equals(getApiLogId(), apiLog.getApiLogId()) &&
                Objects.equals(getUrl(), apiLog.getUrl()) &&
                Objects.equals(getStatusCode(), apiLog.getStatusCode()) &&
                Objects.equals(getMethod(), apiLog.getMethod()) &&
                Objects.equals(getRequest(), apiLog.getRequest()) &&
                Objects.equals(getResponse(), apiLog.getResponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApiLogId(), getUrl(), getStatusCode(), getMethod(), getRequest(), getResponse());
    }
}
