package id.co.bni.ets.lib.base.service;

/**
 * Interface to provide error message of entity service operation.
 *
 * @author Raffi Ditya
 * @see AbstractCrudTrackedService
 * @since 1.0.0.RELEASE
 */
public interface CrudErrorMessage {

    /**
     * Provide entity not found message,
     *
     * @return not found message.
     */
    String getNotFoundMessage();

    /**
     * Provide entity already exists message,
     *
     * @return already exists message.
     */
    String getAlreadyExistsMessage();
}
