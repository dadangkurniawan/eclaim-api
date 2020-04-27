package id.co.bni.ets.lib.base.controller;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.model.request.RetrievableEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.model.ApiResponse;
import id.co.bni.ets.lib.util.UserIdUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;

/**
 * Abstract class for common controller who used crud audited entity.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object entity's identifier.
 * @param <R>  type of request body.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"unchecked"})
public abstract class AbstractCrudController<T extends AbstractTrackedEntity<ID>, ID extends Serializable, R>
        implements CrudOperation<T, ID, R> {

    private static final String CREATED = " created.";
    private static final String UPDATED = " updated.";
    private static final String INACTIVATED = " inactivated.";

    private final CrudServiceOperation<T, ID> service;

    protected AbstractCrudController(CrudServiceOperation<T, ID> service) {
        this.service = service;
    }

    @Override
    public ApiResponse<T> read(ID entityId) {
        return ApiResponse.apiOk(service.findById(entityId));
    }

    private T getEntity(R request) {
        if (request instanceof AbstractTrackedEntity) {
            return (T) request;
        }
        if (request instanceof RetrievableEntity) {
            return ((RetrievableEntity<T>) request).getEntity();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong request.");
    }

    @Override
    public ApiResponse<T> create(R request, OAuth2Authentication authentication) {
        T newEntity = getService().create(getEntity(request), UserIdUtil.getUserId(authentication));

        return ApiResponse.apiOk(newEntity, getEntityName() + CREATED);
    }

    @Override
    public ApiResponse<T> update(R request, ID entityId, OAuth2Authentication authentication) {
        T requestEntity = getEntity(request);
        requestEntity.setId(entityId);
        T updateEntity = getService().update(requestEntity, UserIdUtil.getUserId(authentication));

        return ApiResponse.apiOk(updateEntity, getEntityName() + UPDATED);
    }

    @Override
    public ApiResponse<T> delete(ID entityId, OAuth2Authentication authentication) {
        T deletedEntity = getService().delete(entityId, UserIdUtil.getUserId(authentication));

        return ApiResponse.apiOk(deletedEntity, getEntityName() + INACTIVATED);
    }

    protected CrudServiceOperation<T, ID> getService() {
        return service;
    }

    protected abstract String getEntityName();
}
