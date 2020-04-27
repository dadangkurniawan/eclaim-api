package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_role_module", schema = "dbo")
@AssociationOverrides({
        @AssociationOverride(
                name = "firstEntity",
                joinColumns = @JoinColumn(name = "role_id")
        ),
        @AssociationOverride(
                name = "secondEntity",
                joinColumns = @JoinColumn(name = "module_id")
        )
})
public class RoleModule extends AbstractRelationActiveFlaggedEntity<Long, Role, Module> {

    @Override
    @JsonProperty("role")
    public Role getFirstEntity() {
        return super.firstEntity;
    }

    @Override
    @JsonProperty("module")
    public Module getSecondEntity() {
        return super.secondEntity;
    }
}
