package id.co.bni.ets.lib.base.service.relation;

import id.co.bni.ets.lib.base.service.CrudThrowMessage;
import org.springframework.web.server.ResponseStatusException;

/**
 * Interface to provide implementation of standard {@code ResponseStatusException} thrown in CRUD relation entity.
 *
 * @author Raffi Ditya
 * @see ResponseStatusException
 * @since 1.0.0.RELEASE
 */
public interface CrudRelationThrowMessage extends CrudThrowMessage {

    /**
     * Throw if queried first entity doesn't exists.
     *
     * @return @{ResponseStatusException}
     */
    ResponseStatusException throwFirstEntityNotFound();

    /**
     * Throw if queried second entity doesn't exists..
     *
     * @return @{ResponseStatusException}
     */
    ResponseStatusException throwSecondEntityNotFound();
}
