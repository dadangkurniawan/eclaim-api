package id.co.bni.ets.lib.base.service;

import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Abstract class to provide common method for crud service with soft delete operation.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudActiveFlaggedService
        <T extends AbstractActiveFlaggedEntity<ID>, ID extends Serializable>
        extends AbstractCrudTrackedService<T, ID> {

    protected AbstractCrudActiveFlaggedService(CrudRepository<T, ID> repository) {
        super(repository);
    }

    @Override
    public T delete(ID id, long userId) {
        T entity = findById(id);
        entity.setActiveFlag(Constant.NO_FLAG);
        entity.setLastUpdatedBy(userId);

        return getRepository()
                .save(entity);
    }
}
