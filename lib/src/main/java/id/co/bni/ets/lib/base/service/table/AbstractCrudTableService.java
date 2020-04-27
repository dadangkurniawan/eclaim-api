package id.co.bni.ets.lib.base.service.table;

import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;
import id.co.bni.ets.lib.base.service.AbstractCrudActiveFlaggedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Abstract to provide crud table service.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @see AbstractCrudTableLookupService
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudTableService<T extends AbstractActiveFlaggedEntity<ID>, ID extends Serializable>
        extends AbstractCrudActiveFlaggedService<T, ID>
        implements TableServiceOperation<T> {

    private final PagingAndSortingRepository<T, ID> repository;

    protected AbstractCrudTableService(PagingAndSortingRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<T> getTable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
