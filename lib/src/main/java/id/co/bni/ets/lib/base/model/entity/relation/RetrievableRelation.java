package id.co.bni.ets.lib.base.model.entity.relation;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

/**
 * Interface to provide extraction of related entity.
 *
 * @param <T1> type of first object entity.
 * @param <T2> type of second object entity.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface RetrievableRelation<T1 extends AbstractTrackedEntity<?>, T2 extends AbstractTrackedEntity<?>> {

    /**
     * Get first object entity.
     *
     * @return object entity.
     */
    T1 getFirstEntity();

    /**
     * Get second object entity.
     *
     * @return object entity.
     */
    T2 getSecondEntity();
}
