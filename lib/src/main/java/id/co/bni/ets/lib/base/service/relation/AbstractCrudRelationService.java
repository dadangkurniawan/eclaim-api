package id.co.bni.ets.lib.base.service.relation;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.Optional;

/**
 * Abstract class to provide common method for crud relation entity service.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @see AbstractCrudRelationActiveFlaggedService
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"WeakerAccess", "unused", "rawtypes", "unchecked"})
public abstract class AbstractCrudRelationService
        <T extends AbstractRelationEntity<ID, ? extends AbstractTrackedEntity<?>, ? extends AbstractTrackedEntity<?>>,
                ID extends Serializable>
        implements CrudRelationErrorMessage, CrudRelationThrowMessage, CrudServiceOperation<T, ID> {

    private final CrudRepository<T, ID> relationEntityRepository;
    private final CrudServiceOperation firstEntityService;
    private final CrudServiceOperation secondEntityService;

    protected AbstractCrudRelationService(CrudRepository<T, ID> relationEntityRepository,
                                          CrudServiceOperation<?, ? extends Serializable> firstEntityService,
                                          CrudServiceOperation<?, ? extends Serializable> secondEntityService) {
        this.relationEntityRepository = relationEntityRepository;
        this.firstEntityService = firstEntityService;
        this.secondEntityService = secondEntityService;
    }

    @Override
    public ResponseStatusException throwFirstEntityNotFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, getFirstEntityNotFoundMessage());
    }

    @Override
    public ResponseStatusException throwSecondEntityNotFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, getSecondEntityNotFoundMessage());
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
        return relationEntityRepository.findById(id)
                .orElseThrow(this::throwNotFound);
    }

    protected boolean isRelationsExists(T entity) {
        AbstractTrackedEntity<?> firstEntity = Optional.ofNullable(entity.getFirstEntity())
                .orElseThrow(this::throwFirstEntityNotFound);
        AbstractTrackedEntity<?> secondEntity = Optional.ofNullable(entity.getSecondEntity())
                .orElseThrow(this::throwSecondEntityNotFound);

        if (firstEntity.getId() == null || firstEntityService.findById(firstEntity.getId()) == null) {
            throw throwFirstEntityNotFound();
        }
        if (secondEntity.getId() == null || secondEntityService.findById(secondEntity.getId()) == null) {
            throw throwSecondEntityNotFound();
        }

        return true;
    }

    @Override
    public T create(T entity, long userId) {
        entity.setInitTrack(userId);
        return isRelationsExists(entity) ? relationEntityRepository.save(entity) : null;
    }

    @Override
    public T update(T entity, long userId) {
        T originalEntity = findById(entity.getId());
        entity.prepareUpdate(originalEntity, userId);

        return relationEntityRepository.save(entity);
    }

    @Override
    public T delete(ID id, long userId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "This entity is not removable.");
    }

    protected CrudRepository<T, ID> getRelationEntityRepository() {
        return relationEntityRepository;
    }
}
