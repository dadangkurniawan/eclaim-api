package id.co.bni.ets.lib.base.service;

import java.util.List;

/**
 * Interface to provide additional methods to retrieve entities using search term.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @see javax.persistence.Entity
 * @since 1.0.0.RELEASE
 */
public interface SearchServiceOperation<T> {

    /**
     * Get list of entity with given search term.
     *
     * @param searchTerm search term.
     *
     * @return {@code List} of requested entity.
     */
    List<T> findSuggestions(String searchTerm);
}
