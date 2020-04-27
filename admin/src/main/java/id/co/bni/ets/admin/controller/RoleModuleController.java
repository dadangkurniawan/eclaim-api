package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminRelationController;
import id.co.bni.ets.admin.model.RoleModuleRequest;
import id.co.bni.ets.admin.service.RoleModuleService;
import id.co.bni.ets.lib.model.entity.RoleModule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-module")
public class RoleModuleController
        extends AbstractAdminRelationController<RoleModule, Long, RoleModuleRequest, Integer> {

    public RoleModuleController(RoleModuleService roleModuleService) {
        super(roleModuleService);
    }

    @Override
    protected String getEntityName() {
        return "Role Module";
    }
}
