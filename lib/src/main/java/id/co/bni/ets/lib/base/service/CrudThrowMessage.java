package id.co.bni.ets.lib.base.service;

import org.springframework.web.server.ResponseStatusException;

/**
 * Interface to provide implementation of standard {@code ResponseStatusException} thrown in CRUD entity.
 *
 * @author Raffi Ditya
 * @see ResponseStatusException
 * @since 1.0.0.RELEASE
 */
public interface CrudThrowMessage {

    /**
     * Throw if queried entity doesn't exists.
     *
     * @return @{ResponseStatusException}
     */
    ResponseStatusException throwNotFound();

    /**
     * Throw if queried entity already exists. Usually used when create new entity with existed ID.
     *
     * @return @{ResponseStatusException}
     */
    ResponseStatusException throwAlreadyExists();
}
