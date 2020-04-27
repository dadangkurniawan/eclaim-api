package id.co.bni.ets.admin.base.controller;

import id.co.bni.ets.lib.base.controller.table.AbstractCrudTableLookupController;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.SearchServiceOperation;
import id.co.bni.ets.lib.base.service.table.TableServiceOperation;
import id.co.bni.ets.lib.log.annotation.LogApi;
import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * Facade class to map admin crud with table and lookup operation and join {@code LogApi} with {@code Mapping}.
 * <p>
 * Note : {@code LogApi} requires to rewrite Spring's controller mapping to work because java doesn't inherit annotation
 * on interface. On the other hand, Spring uses bean scanning so several annotation (like {@code PreAuthorize}) works on
 * interface.
 * <p>
 * Any re-write of methods are intended to join {@code PreAuthorize} with controller to works.
 *
 * @param <T>  type of entity.
 * @param <ID> type of entity's identifier.
 * @param <R>  type of request body.
 *
 * @author Raffi Ditya
 * @see AbstractAdminController
 * @see AbstractAdminRelationController
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractAdminLookupController<T extends AbstractTrackedEntity<ID>, ID extends Serializable, R>
        extends AbstractCrudTableLookupController<T, ID, R>
        implements AdminCrudAuthorize<T, ID, R>, AdminTableAuthorize<T>, AdminSearchAuthorize<T> {

    protected <S extends CrudServiceOperation<T, ID> & TableServiceOperation<T> & SearchServiceOperation<T>>
    AbstractAdminLookupController(S service) {
        super(service);
    }

    @Override
    @GetMapping("/{entityId}")
    public ApiResponse<T> read(@PathVariable ID entityId) {
        return super.read(entityId);
    }

    @Override
    @LogApi
    @PostMapping
    public ApiResponse<T> create(@Valid @RequestBody R request, OAuth2Authentication authentication) {
        return super.create(request, authentication);
    }

    @Override
    @LogApi
    @PutMapping("/{entityId}")
    public ApiResponse<T> update(@Valid @RequestBody R request, @PathVariable ID entityId,
                                 OAuth2Authentication authentication) {
        return super.update(request, entityId, authentication);
    }

    @Override
    @LogApi
    @DeleteMapping("/{entityId}")
    public ApiResponse<T> delete(@PathVariable ID entityId, OAuth2Authentication authentication) {
        return super.delete(entityId, authentication);
    }

    @Override
    public Page<T> getTable(@RequestParam(required = false, defaultValue = "") String searchTerm, Pageable pageable) {
        return super.getTable(searchTerm, pageable);
    }

    @Override
    public ApiResponse<List<T>> getSuggestions(@RequestParam String searchTerm) {
        return super.getSuggestions(searchTerm);
    }
}
