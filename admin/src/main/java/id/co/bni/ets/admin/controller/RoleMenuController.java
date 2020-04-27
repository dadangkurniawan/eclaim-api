package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminRelationController;
import id.co.bni.ets.admin.model.RoleMenuRequest;
import id.co.bni.ets.admin.service.RoleMenuService;
import id.co.bni.ets.lib.model.entity.RoleMenu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-menu")
public class RoleMenuController extends AbstractAdminRelationController<RoleMenu, Long, RoleMenuRequest, Integer> {

    public RoleMenuController(RoleMenuService roleMenuService) {
        super(roleMenuService);
    }

    @Override
    protected String getEntityName() {
        return "Role Menu";
    }
}
