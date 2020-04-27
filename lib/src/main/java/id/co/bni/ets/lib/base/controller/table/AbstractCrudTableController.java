package id.co.bni.ets.lib.base.controller.table;

import id.co.bni.ets.lib.base.controller.AbstractCrudController;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.table.TableServiceOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Abstract class to provide crud table request of entity.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object entity's identifier.
 * @param <R>  type of request body.
 *
 * @author Raffi Ditya
 * @see AbstractCrudTableLookupController
 * @see id.co.bni.ets.lib.base.controller.table.relation.AbstractCrudRelationTableController
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudTableController
        <T extends AbstractTrackedEntity<ID>, ID extends Serializable, R>
        extends AbstractCrudController<T, ID, R>
        implements TableOperation<T> {

    private final TableServiceOperation<T> tableService;

    protected <S extends CrudServiceOperation<T, ID> & TableServiceOperation<T>>
    AbstractCrudTableController(S service) {
        super(service);
        this.tableService = service;
    }

    @Override
    public Page<T> getTable(String searchTerm, Pageable pageable) {
        return searchTerm.isEmpty() ? tableService.getTable(pageable)
                                    : tableService.findTable(searchTerm, pageable);
    }
}
