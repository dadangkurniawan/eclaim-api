package id.co.bni.ets.lib.base.controller;

import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Interface to provide common mapping controller.
 *
 * @param <T>  type of object entity.
 * @param <ID> type of object entity's identifier.
 * @param <R>  type of request body.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public interface CrudOperation<T, ID, R> {

    /**
     * Provide get entity by id mapping.
     *
     * @param entityId id of the entity object.
     *
     * @return entity object.
     */
    @GetMapping("/{entityId}")
    ApiResponse<T> read(@PathVariable ID entityId);

    /**
     * Provide post entity to create new entity.
     *
     * @param request        request body.
     * @param authentication oauth authentication object.
     *
     * @return api message success if entity created.
     */
    @PostMapping
    ApiResponse<T> create(@Valid @RequestBody R request, final OAuth2Authentication authentication);

    /**
     * Provide put mapping to edit existed entity.
     *
     * @param request        request body.
     * @param entityId       id of the entity object.
     * @param authentication oauth authentication object.
     *
     * @return api message success if entity created.
     */
    @PutMapping("/{entityId}")
    ApiResponse<T> update(@RequestBody @Valid R request, @PathVariable ID entityId,
                          final OAuth2Authentication authentication);

    /**
     * Provide delete mapping to remove existed entity.
     *
     * @param entityId       id of entity object.
     * @param authentication oauth authentication object.
     *
     * @return api message success if entity removed.
     */
    @DeleteMapping("/{entityId}")
    ApiResponse<T> delete(@PathVariable ID entityId, final OAuth2Authentication authentication);
}
