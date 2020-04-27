package id.co.bni.ets.lib.base.service.relation;

import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * Abstract class to provide common method for crud relation entity with soft delete operation service.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object entity identifier.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudRelationActiveFlaggedService
        <T extends AbstractRelationActiveFlaggedEntity<ID, ? extends AbstractTrackedEntity<?>,
                ? extends AbstractTrackedEntity<?>>, ID extends Serializable>
        extends AbstractCrudRelationService<T, ID> {

    protected AbstractCrudRelationActiveFlaggedService(CrudRepository<T, ID> relationEntityRepository,
                                                       CrudServiceOperation<?, ? extends Serializable>
                                                               firstEntityService,
                                                       CrudServiceOperation<?, ? extends Serializable>
                                                               secondEntityService) {
        super(relationEntityRepository, firstEntityService, secondEntityService);
    }

    @Override
    public T delete(ID id, long userId) {
        T entity = findById(id);
        Character activeFlag = Optional.ofNullable(entity.getActiveFlag())
                .orElse(Constant.NO_FLAG);
        if (Constant.YES_FLAG == activeFlag) {
            Date now = new Date();
            int compareResult = now.compareTo(Optional.ofNullable(entity.getEndDate())
                                                      .orElse(new Date()));
            if (compareResult < 0) {
                entity.setEndDate(now);
            }
        }
        entity.setActiveFlag(Constant.NO_FLAG);
        entity.setLastUpdatedBy(userId);

        return getRelationEntityRepository()
                .save(entity);
    }
}
