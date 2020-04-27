package id.co.bni.ets.admin.model;

import id.co.bni.ets.lib.base.model.request.RetrievableEntity;
import id.co.bni.ets.lib.model.entity.Privilege;
import id.co.bni.ets.lib.model.entity.Role;
import id.co.bni.ets.lib.model.entity.RolePrivilege;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class RolePrivilegeRequest implements RetrievableEntity<RolePrivilege> {

    @Positive(message = "Wrong role id.")
    private Integer roleId;

    @Positive(message = "Wrong privilege id.")
    private Integer privilegeId;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public RolePrivilege getEntity() {
        RolePrivilege rolePrivilege = new RolePrivilege();
        Role role = new Role();
        Privilege privilege = new Privilege();

        role.setId(roleId);
        privilege.setId(privilegeId);
        rolePrivilege.setFirstEntity(role);
        rolePrivilege.setSecondEntity(privilege);
        rolePrivilege.setCanCreate(canCreate);
        rolePrivilege.setCanRead(canRead);
        rolePrivilege.setCanUpdate(canUpdate);
        rolePrivilege.setCanDelete(canDelete);

        return rolePrivilege;
    }
}
