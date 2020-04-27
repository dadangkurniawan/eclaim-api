package id.co.bni.ets.lib.base.controller.table.relation;

import id.co.bni.ets.lib.base.controller.AbstractCrudController;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.relation.table.TableRelationServiceOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Abstract class to provide crud table relation entity request.
 *
 * @param <T>   type of object entity.
 * @param <ID>  type of object entity's id.
 * @param <R>   type of request body.
 * @param <IDR> type of object referer relation id.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudRelationTableController
        <T extends AbstractTrackedEntity<ID>, ID extends Serializable, R, IDR extends Serializable>
        extends AbstractCrudController<T, ID, R>
        implements TableRelationOperation<T, IDR> {

    private final TableRelationServiceOperation<T, IDR> service;

    protected <S extends CrudServiceOperation<T, ID> & TableRelationServiceOperation<T, IDR>>
    AbstractCrudRelationTableController(S service) {
        super(service);
        this.service = service;
    }

    @Override
    public Page<T> getTable(IDR relationId, String searchTerm, Pageable pageable) {
        return searchTerm.isEmpty() ? service.getTable(relationId, pageable)
                                    : service.findTable(relationId, searchTerm, pageable);
    }
}
