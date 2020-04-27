package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminRelationController;
import id.co.bni.ets.admin.model.RolePrivilegeRequest;
import id.co.bni.ets.admin.service.RolePrivilegeService;
import id.co.bni.ets.lib.model.entity.RolePrivilege;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-privilege")
public class RolePrivilegeController
        extends AbstractAdminRelationController<RolePrivilege, Long, RolePrivilegeRequest, Integer> {

    public RolePrivilegeController(RolePrivilegeService rolePrivilegeService) {
        super(rolePrivilegeService);
    }

    @Override
    protected String getEntityName() {
        return "Role Privilege";
    }
}
