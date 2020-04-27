package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AbstractAdminLookupController;
import id.co.bni.ets.admin.service.OrganizationService;
import id.co.bni.ets.lib.model.entity.Organization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController extends AbstractAdminLookupController<Organization, Long, Organization> {

    public OrganizationController(OrganizationService organizationService) {
        super(organizationService);
    }

    @Override
    protected String getEntityName() {
        return "Organization";
    }
}
