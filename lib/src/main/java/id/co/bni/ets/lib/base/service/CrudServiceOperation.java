package id.co.bni.ets.lib.base.service;

/**
 * Interface to provide additional methods for CRUD entity.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of entity's identifier.
 *
 * @author Raffi Ditya
 * @see javax.persistence.Entity
 * @since 1.0.0.RELEASE
 */
public interface CrudServiceOperation<T, ID> {

    /**
     * Get entity by id.
     *
     * @param id entity's identifier.
     *
     * @return requested entity.
     */
    T findById(ID id);

    /**
     * Persist new entity.
     *
     * @param entity entity to be persisted.
     * @param userId user id who doing the operation.
     *
     * @return saved entity.
     */
    T create(T entity, long userId);

    /**
     * Update entity.
     *
     * @param entity entity to be updated.
     * @param userId user id who doing the operation.
     *
     * @return saved entity.
     */
    T update(T entity, long userId);

    /**
     * Delete or remove entity. Usually entity soft removed by update it's {@code activeFlag} to {@code 'N'}.
     *
     * @param id     entity's id to be removed.
     * @param userId user id who doing the operation.
     *
     * @return deleted entity.
     */
    T delete(ID id, long userId);
}
