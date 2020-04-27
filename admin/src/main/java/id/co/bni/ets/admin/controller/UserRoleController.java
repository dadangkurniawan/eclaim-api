package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminRelationController;
import id.co.bni.ets.admin.model.UserRoleRequest;
import id.co.bni.ets.admin.service.UserRoleService;
import id.co.bni.ets.lib.model.entity.UserRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-role")
public class UserRoleController extends AbstractAdminRelationController<UserRole, Long, UserRoleRequest, Long> {

    public UserRoleController(UserRoleService userRoleService) {
        super(userRoleService);
    }

    @Override
    protected String getEntityName() {
        return "User Role";
    }
}
