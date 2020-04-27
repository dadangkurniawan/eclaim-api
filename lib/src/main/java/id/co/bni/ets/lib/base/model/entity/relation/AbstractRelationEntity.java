package id.co.bni.ets.lib.base.model.entity.relation;

import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class of relation entity.
 *
 * @param <ID> type of object entity's identifier
 * @param <T1> type of first object entity.
 * @param <T2> type of second object entity.
 * @author Raffi Ditya
 * @since 1.0.0.RELEASE
 */
@SuppressWarnings({"unchecked", "unused"})
@MappedSuperclass
public abstract class AbstractRelationEntity
        <ID extends Serializable, T1 extends AbstractTrackedEntity<?>, T2 extends AbstractTrackedEntity<?>>
        extends AbstractTrackedEntity<ID>
        implements RetrievableRelation<T1, T2> {

    @ManyToOne
    protected T1 firstEntity;

    @ManyToOne
    protected T2 secondEntity;

    // Getter removed so object entity must implement it with json property mapping name.

    public void setFirstEntity(T1 firstEntity) {
        this.firstEntity = firstEntity;
    }

    public void setSecondEntity(T2 secondEntity) {
        this.secondEntity = secondEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        AbstractRelationEntity<ID, T1, T2> that = (AbstractRelationEntity<ID, T1, T2>) o;

        return Objects.equals(firstEntity, that.firstEntity) &&
                Objects.equals(secondEntity, that.secondEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstEntity, secondEntity);
    }
}
