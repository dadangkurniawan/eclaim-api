package id.co.bni.ets.admin.model;

import id.co.bni.ets.lib.base.model.request.AbstractRelationActiveFLaggedRequest;
import id.co.bni.ets.lib.model.entity.Role;
import id.co.bni.ets.lib.model.entity.User;
import id.co.bni.ets.lib.model.entity.UserRole;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class UserRoleRequest extends AbstractRelationActiveFLaggedRequest<UserRole> {

    @Positive(message = "Wrong user id.")
    private Long userId;

    @Positive(message = "Wrong role id.")
    private Integer roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public UserRole getEntity() {
        UserRole userRole = new UserRole();
        Role role = new Role();
        User user = new User();

        role.setId(roleId);
        user.setId(userId);
        userRole.setFirstEntity(user);
        userRole.setSecondEntity(role);
        userRole.setActiveFlag(activeFlag);
        userRole.setEndDate(endDate);
        userRole.setStartDate(startDate);

        return userRole;
    }
}
