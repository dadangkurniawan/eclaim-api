package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminRelationController;
import id.co.bni.ets.admin.model.UserOrganizationRequest;
import id.co.bni.ets.admin.service.UserOrganizationService;
import id.co.bni.ets.lib.model.entity.UserOrganization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-org")
public class UserOrganizationController
        extends AbstractAdminRelationController<UserOrganization, Long, UserOrganizationRequest, Long> {

    public UserOrganizationController(UserOrganizationService userOrganizationService) {
        super(userOrganizationService);
    }

    @Override
    protected String getEntityName() {
        return "User Organization";
    }
}
