package id.co.bni.ets.admin.model;

import id.co.bni.ets.lib.base.model.request.AbstractRelationActiveFLaggedRequest;
import id.co.bni.ets.lib.model.entity.Menu;
import id.co.bni.ets.lib.model.entity.Role;
import id.co.bni.ets.lib.model.entity.RoleMenu;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class RoleMenuRequest extends AbstractRelationActiveFLaggedRequest<RoleMenu> {

    @Positive(message = "Wrong role id.")
    private Integer roleId;

    @Positive(message = "Wrong menu id.")
    private Integer menuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public RoleMenu getEntity() {
        RoleMenu roleMenu = new RoleMenu();
        Role role = new Role();
        Menu menu = new Menu();

        role.setId(roleId);
        menu.setId(menuId);
        roleMenu.setFirstEntity(role);
        roleMenu.setSecondEntity(menu);
        roleMenu.setActiveFlag(activeFlag);
        roleMenu.setStartDate(startDate);
        roleMenu.setEndDate(endDate);

        return roleMenu;
    }
}
