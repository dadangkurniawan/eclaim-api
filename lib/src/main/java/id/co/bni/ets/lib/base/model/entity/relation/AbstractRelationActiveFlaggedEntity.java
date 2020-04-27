package id.co.bni.ets.lib.base.model.entity.relation;

import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Abstract class of relation entity with {@code activeFlag} column.
 *
 * @param <ID> type of object entity's identifier
 * @param <T1> type of first object entity.
 * @param <T2> type of second object entity.
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"unused", "unchecked"})
@MappedSuperclass
public abstract class AbstractRelationActiveFlaggedEntity
        <ID extends Serializable, T1 extends AbstractTrackedEntity<?>, T2 extends AbstractTrackedEntity<?>>
        extends AbstractRelationEntity<ID, T1, T2> {

    protected Character activeFlag;

    @Temporal(TemporalType.DATE)
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    protected Date endDate;

    public Character getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Character activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @PrePersist
    @PreUpdate
    private void prepareSaving() {
        if (activeFlag == null || Constant.YES_FLAG != Character.toUpperCase(activeFlag)) {
            activeFlag = Constant.NO_FLAG;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        AbstractRelationActiveFlaggedEntity<ID, T1, T2> that = (AbstractRelationActiveFlaggedEntity<ID, T1, T2>) o;

        return Objects.equals(activeFlag, that.activeFlag) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), activeFlag, startDate, endDate);
    }
}
