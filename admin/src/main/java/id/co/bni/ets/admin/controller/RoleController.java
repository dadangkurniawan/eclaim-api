package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminLookupController;
import id.co.bni.ets.admin.service.RoleService;
import id.co.bni.ets.lib.model.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractAdminLookupController<Role, Integer, Role> {

    public RoleController(RoleService roleService) {
        super(roleService);
    }

    @Override
    protected String getEntityName() {
        return "Role";
    }
}
