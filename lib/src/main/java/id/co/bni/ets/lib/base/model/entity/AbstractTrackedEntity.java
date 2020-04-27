package id.co.bni.ets.lib.base.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Abstract class for base crud audited entity.
 *
 * @param <ID> type of object entity's identifier.
 *
 * @author Raffi Ditya
 * @see AbstractActiveFlaggedEntity
 * @see AbstractRelationEntity
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"unused", "unchecked"})
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"creationDate", "createdBy", "lastUpdatedDate", "lastUpdatedBy"},
        allowGetters = true
)
public abstract class AbstractTrackedEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    protected Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedDate;

    protected Long lastUpdatedBy;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setInitTrack(long userId) {
        this.createdBy = userId;
        this.lastUpdatedBy = userId;
    }

    public void prepareUpdate(@NotNull AbstractTrackedEntity<?> sourceEntity, long userId) {
        createdBy = sourceEntity.getCreatedBy();
        creationDate = sourceEntity.getCreationDate();
        lastUpdatedBy = userId;
    }

    @PrePersist
    private void preCreate() {
        creationDate = new Date();
        lastUpdatedDate = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        lastUpdatedDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTrackedEntity<ID> that = (AbstractTrackedEntity<ID>) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
                Objects.equals(lastUpdatedBy, that.lastUpdatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, createdBy, lastUpdatedDate, lastUpdatedBy);
    }
}
