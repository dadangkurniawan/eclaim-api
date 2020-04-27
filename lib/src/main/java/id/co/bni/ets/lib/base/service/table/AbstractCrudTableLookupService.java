package id.co.bni.ets.lib.base.service.table;

import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;
import id.co.bni.ets.lib.base.service.SearchServiceOperation;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Facade class to provide table service with search suggestion lookup.
 *
 * @param <T>  type of object entity
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudTableLookupService<T extends AbstractActiveFlaggedEntity<ID>, ID extends Serializable>
        extends AbstractCrudTableService<T, ID>
        implements SearchServiceOperation<T> {

    protected AbstractCrudTableLookupService(PagingAndSortingRepository<T, ID> repository) {
        super(repository);
    }
}
