package id.co.bni.ets.lib.base.service;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;

/**
 * Abstract class to provide common method for crud service.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @see AbstractCrudActiveFlaggedService
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudTrackedService<T extends AbstractTrackedEntity<ID>, ID extends Serializable>
        implements CrudErrorMessage, CrudThrowMessage, CrudServiceOperation<T, ID> {

    private final CrudRepository<T, ID> repository;

    protected AbstractCrudTrackedService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public ResponseStatusException throwNotFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, getNotFoundMessage());
    }

    @Override
    public ResponseStatusException throwAlreadyExists() {
        return new ResponseStatusException(HttpStatus.CONFLICT, getAlreadyExistsMessage());
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(this::throwNotFound);
    }

    @Override
    public T create(T entity, long userId) {
        if (entity.getId() != null) {
            throw throwAlreadyExists();
        }

        entity.setInitTrack(userId);
        return repository.save(entity);
    }

    @Override
    public T update(T entity, long userId) {
        T originalEntity = findById(entity.getId());
        entity.prepareUpdate(originalEntity, userId);

        return repository.save(entity);
    }

    @Override
    public T delete(ID id, long userId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                                          "This entity does not support DELETE operations.");
    }

    public CrudRepository<T, ID> getRepository() {
        return repository;
    }
}
