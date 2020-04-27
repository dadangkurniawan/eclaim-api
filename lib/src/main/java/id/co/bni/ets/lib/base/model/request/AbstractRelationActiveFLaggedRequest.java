package id.co.bni.ets.lib.base.model.request;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Abstract class of relation request body with {@code activeFlag} column.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings("unused")
public abstract class AbstractRelationActiveFLaggedRequest<T extends AbstractTrackedEntity<?>>
        implements RetrievableEntity<T> {

    @NotNull(message = "Start date can't be null.")
    protected Date endDate;

    @NotNull(message = "End date can't be null.")
    protected Date startDate;

    protected Character activeFlag;

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

    public Character getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Character activeFlag) {
        this.activeFlag = activeFlag;
    }
}
