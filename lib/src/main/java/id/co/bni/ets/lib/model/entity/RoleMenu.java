package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationActiveFlaggedEntity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_role_menu", schema = "dbo")
@AssociationOverrides({
        @AssociationOverride(
                name = "firstEntity",
                joinColumns = @JoinColumn(name = "role_id")
        ),
        @AssociationOverride(
                name = "secondEntity",
                joinColumns = @JoinColumn(name = "menu_id")
        )
})
public class RoleMenu extends AbstractRelationActiveFlaggedEntity<Long, Role, Menu> {

    @Override
    @JsonProperty("role")
    public Role getFirstEntity() {
        return super.firstEntity;
    }

    @Override
    @JsonProperty("menu")
    public Menu getSecondEntity() {
        return super.secondEntity;
    }
}
