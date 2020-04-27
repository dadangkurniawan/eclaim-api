package id.co.bni.ets.lib.log.model.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class ApiLogId implements Serializable {

    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiLogId)) return false;
        ApiLogId apiLogId = (ApiLogId) o;
        return Objects.equals(getUserId(), apiLogId.getUserId()) &&
                Objects.equals(getDate(), apiLogId.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getDate());
    }
}
