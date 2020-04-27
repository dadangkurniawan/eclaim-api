package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_user_org", schema = "dbo")
@AssociationOverrides({
        @AssociationOverride(
                name = "firstEntity",
                joinColumns = @JoinColumn(name = "user_id")
        ),
        @AssociationOverride(
                name = "secondEntity",
                joinColumns = @JoinColumn(name = "organization_id")
        )
})
public class UserOrganization extends AbstractRelationActiveFlaggedEntity<Long, User, Organization> {

    @Override
    @JsonProperty("user")
    public User getFirstEntity() {
        return super.firstEntity;
    }

    @Override
    @JsonProperty("organization")
    public Organization getSecondEntity() {
        return super.secondEntity;
    }
}
