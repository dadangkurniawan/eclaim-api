package id.co.bni.ets.lib.base.controller.table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface to provide table operation request.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface TableOperation<T> {

    /**
     * provide get mapping to retrieve paged entity.
     *
     * @param searchTerm non-mandatory search term text.
     * @param pageable   pageable request.
     *
     * @return paged entity.
     */
    @GetMapping
    Page<T> getTable(@RequestParam(required = false, defaultValue = "") String searchTerm, Pageable pageable);
}
