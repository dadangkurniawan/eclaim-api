package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_user_role", schema = "dbo")
@AssociationOverrides({
        @AssociationOverride(
                name = "firstEntity",
                joinColumns = @JoinColumn(name = "user_id")
        ),
        @AssociationOverride(
                name = "secondEntity",
                joinColumns = @JoinColumn(name = "role_id")
        )
})
public class UserRole extends AbstractRelationActiveFlaggedEntity<Long, User, Role> {

    @Override
    @JsonProperty("user")
    public User getFirstEntity() {
        return super.firstEntity;
    }

    @Override
    @JsonProperty("role")
    public Role getSecondEntity() {
        return super.secondEntity;
    }
}
