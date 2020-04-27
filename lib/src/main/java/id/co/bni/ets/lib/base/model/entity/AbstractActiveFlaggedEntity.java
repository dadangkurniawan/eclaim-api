package id.co.bni.ets.lib.base.model.entity;

import id.co.bni.ets.lib.Constant;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class for additional {@code activeFlag}-ed column.
 *
 * @param <ID> type of entity's identifier.
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings("unchecked")
@MappedSuperclass
public abstract class AbstractActiveFlaggedEntity<ID extends Serializable> extends AbstractTrackedEntity<ID> {

    protected Character activeFlag;

    public Character getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Character activeFlag) {
        this.activeFlag = activeFlag;
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
        AbstractActiveFlaggedEntity<ID> that = (AbstractActiveFlaggedEntity<ID>) o;

        return Objects.equals(activeFlag, that.activeFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), activeFlag);
    }
}
