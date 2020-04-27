package id.co.bni.ets.lib.base.service.relation.table;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.relation.AbstractCrudRelationActiveFlaggedService;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Facade class to provide crud active flagged table relation entity service.
 *
 * @param <T>   type of object relation.
 * @param <ID>  type of entity's identifier.
 * @param <IDR> type of referer entity's relation identifier.
 *
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
public abstract class AbstractCrudRelationTableService
        <T extends AbstractRelationActiveFlaggedEntity<ID, ? extends AbstractTrackedEntity<?>,
                ? extends AbstractTrackedEntity<?>>, ID extends Serializable, IDR extends Serializable>
        extends AbstractCrudRelationActiveFlaggedService<T, ID>
        implements TableRelationServiceOperation<T, IDR> {

    protected AbstractCrudRelationTableService(CrudRepository<T, ID> relationEntityRepository,
                                               CrudServiceOperation<?, IDR> firstEntityService,
                                               CrudServiceOperation<?, ? extends Serializable> secondEntityService) {
        super(relationEntityRepository, firstEntityService, secondEntityService);
    }
}
