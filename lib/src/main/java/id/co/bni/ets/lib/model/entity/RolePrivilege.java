package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.relation.AbstractRelationEntity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_role_privilege", schema = "dbo")
@AssociationOverrides({
        @AssociationOverride(
                name = "firstEntity",
                joinColumns = @JoinColumn(name = "role_id")
        ),
        @AssociationOverride(
                name = "secondEntity",
                joinColumns = @JoinColumn(name = "privilege_id")
        )
})
public class RolePrivilege extends AbstractRelationEntity<Long, Role, Privilege> {

    private Character canCreate;

    private Character canRead;

    private Character canUpdate;

    private Character canDelete;

    public Character getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Character canCreate) {
        this.canCreate = canCreate;
    }

    public Character getCanRead() {
        return canRead;
    }

    public void setCanRead(Character canRead) {
        this.canRead = canRead;
    }

    public Character getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Character canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Character getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Character canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    @JsonProperty("role")
    public Role getFirstEntity() {
        return firstEntity;
    }

    @Override
    @JsonProperty("privilege")
    public Privilege getSecondEntity() {
        return secondEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        RolePrivilege that = (RolePrivilege) o;

        return Objects.equals(canCreate, that.canCreate) &&
                Objects.equals(canRead, that.canRead) &&
                Objects.equals(canUpdate, that.canUpdate) &&
                Objects.equals(canDelete, that.canDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), canCreate, canRead, canUpdate, canDelete);
    }
}
