package id.co.bni.ets.lib.base.controller.table.relation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface to provide table relation operation request.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object referer relation entity's identifier.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface TableRelationOperation<T, ID> {

    /**
     * Provide get mapping to retrieve paged entity.
     *
     * @param relationId referer relation entity's identifier.
     * @param searchTerm non-mandatory search term text.
     * @param pageable   pageable request.
     *
     * @return paged entity.
     */
    @GetMapping
    Page<T> getTable(@RequestParam ID relationId, @RequestParam(required = false, defaultValue = "") String searchTerm,
                     Pageable pageable);
}
