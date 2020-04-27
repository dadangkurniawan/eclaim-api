package id.co.bni.ets.admin.base.controller;

import id.co.bni.ets.lib.base.controller.table.relation.AbstractCrudRelationTableController;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.relation.table.TableRelationServiceOperation;
import id.co.bni.ets.lib.log.annotation.LogApi;
import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Facade class to map admin crud relation-ed entity with table operation and join {@code LogApi} with {@code Mapping}.
 * <p>
 * Note : {@code LogApi} requires to rewrite Spring's controller mapping to work because java doesn't inherit annotation
 * on interface. On the other hand, Spring uses bean scanning so several annotation (like {@code PreAuthorize}) works on
 * interface.
 * <p>
 * Any re-write of methods are intended to join {@code PreAuthorize} with controller to works.
 *
 * @param <T>   type of entity.
 * @param <ID>  type of entity's identifier.
 * @param <R>   type of request body.
 * @param <IDR> type of referenced entity's identifier.
 *
 * @author Raffi Ditya
 * @see AbstractAdminController
 * @see AbstractAdminLookupController
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractAdminRelationController
        <T extends AbstractTrackedEntity<ID>, ID extends Serializable, R, IDR extends Serializable>
        extends AbstractCrudRelationTableController<T, ID, R, IDR>
        implements AdminCrudAuthorize<T, ID, R>, AdminRelationTableAuthorize<T, IDR> {

    protected <S extends CrudServiceOperation<T, ID> & TableRelationServiceOperation<T, IDR>>
    AbstractAdminRelationController(S service) {
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
    public Page<T> getTable(@RequestParam IDR relationId,
                            @RequestParam(required = false, defaultValue = "") String searchTerm, Pageable pageable) {
        return super.getTable(relationId, searchTerm, pageable);
    }
}
