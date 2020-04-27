package id.co.bni.ets.lib.base.service.relation;

import id.co.bni.ets.lib.base.service.AbstractCrudTrackedService;
import id.co.bni.ets.lib.base.service.CrudErrorMessage;

/**
 * Interface to provide error message of relation entity service operation.
 *
 * @author Raffi Ditya
 * @see AbstractCrudTrackedService
 * @since 1.0.0.RELEASE
 */
public interface CrudRelationErrorMessage extends CrudErrorMessage {

    /**
     * Provide first entity not found message,
     *
     * @return not found message.
     */
    String getFirstEntityNotFoundMessage();

    /**
     * Provide second entity not found message,
     *
     * @return not found message.
     */
    String getSecondEntityNotFoundMessage();
}
