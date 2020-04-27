package id.co.bni.ets.lib.base.controller;

import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface to get search suggestion by specific search term.
 *
 * @param <T> type of object entity.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface SearchOperation<T> {

    /**
     * Provide get mapping to get list of suggested entity by specific search term.
     *
     * @param searchTerm search term text.
     *
     * @return list of suggested search term.
     */
    @GetMapping("/searchSuggestions")
    ApiResponse<List<T>> getSuggestions(@RequestParam String searchTerm);
}
