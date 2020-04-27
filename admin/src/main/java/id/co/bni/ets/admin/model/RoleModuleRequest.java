package id.co.bni.ets.admin.model;

import id.co.bni.ets.lib.base.model.request.AbstractRelationActiveFLaggedRequest;
import id.co.bni.ets.lib.base.model.request.RetrievableEntity;
import id.co.bni.ets.lib.model.entity.Module;
import id.co.bni.ets.lib.model.entity.Role;
import id.co.bni.ets.lib.model.entity.RoleModule;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class RoleModuleRequest extends AbstractRelationActiveFLaggedRequest<RoleModule> {

    @Positive(message = "Wrong role id.")
    private Integer roleId;

    @Positive(message = "Wrong role id.")
    private Integer moduleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public RoleModule getEntity() {
        RoleModule roleModule = new RoleModule();
        Role role = new Role();
        Module module = new Module();

        role.setId(roleId);
        module.setId(moduleId);
        roleModule.setFirstEntity(role);
        roleModule.setSecondEntity(module);
        roleModule.setActiveFlag(activeFlag);
        roleModule.setStartDate(startDate);
        roleModule.setEndDate(endDate);

        return roleModule;
    }
}
