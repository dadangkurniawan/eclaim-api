package id.co.bni.ets.lib.base.controller.table;

import id.co.bni.ets.lib.base.controller.SearchOperation;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.SearchServiceOperation;
import id.co.bni.ets.lib.base.service.table.TableServiceOperation;
import id.co.bni.ets.lib.model.ApiResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract class to provide crud table request with search suggestion lookup.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object entity's identifier.
 * @param <R>  type of request body.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudTableLookupController
        <T extends AbstractTrackedEntity<ID>, ID extends Serializable, R>
        extends AbstractCrudTableController<T, ID, R>
        implements SearchOperation<T> {

    private final SearchServiceOperation<T> lookupService;

    protected <S extends CrudServiceOperation<T, ID> & TableServiceOperation<T> & SearchServiceOperation<T>>
    AbstractCrudTableLookupController(S service) {
        super(service);
        this.lookupService = service;
    }

    @Override
    public ApiResponse<List<T>> getSuggestions(String searchTerm) {
        List<T> suggestionList = lookupService.findSuggestions(searchTerm);

        return ApiResponse.apiOk(suggestionList);
    }
}
