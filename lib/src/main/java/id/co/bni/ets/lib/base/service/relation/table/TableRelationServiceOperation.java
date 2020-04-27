package id.co.bni.ets.lib.base.service.relation.table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface provide additional methods to retrieve paged relation entity.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @see javax.persistence.Entity
 * @since 1.0.0.RELEASE
 */
public interface TableRelationServiceOperation<T, ID> {

    /**
     * Get paged entity with given searchTerm and page request.
     *
     * @param searchTerm search term for searching entity by specific column's value.
     * @param pageable   page request.
     *
     * @return the requested {@code Page} entity.
     */
    Page<T> findTable(ID entityRelationId, String searchTerm, Pageable pageable);

    /**
     * Get paged entity with given page request.
     *
     * @param pageable page request.
     *
     * @return the requested {@code Page} entity.
     */
    Page<T> getTable(ID entityRelationId, Pageable pageable);
}
