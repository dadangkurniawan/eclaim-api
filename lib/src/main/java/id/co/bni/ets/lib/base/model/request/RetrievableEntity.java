package id.co.bni.ets.lib.base.model.request;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

/**
 * Interface to provide extraction entity from body request.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface RetrievableEntity<T extends AbstractTrackedEntity<?>> {

    /**
     * Get entity object.
     *
     * @return entity object.
     */
    T getEntity();
}
