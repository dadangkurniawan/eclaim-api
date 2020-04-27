package id.co.bni.ets.admin.model;

import id.co.bni.ets.lib.base.model.request.AbstractRelationActiveFLaggedRequest;
import id.co.bni.ets.lib.model.entity.Organization;
import id.co.bni.ets.lib.model.entity.User;
import id.co.bni.ets.lib.model.entity.UserOrganization;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class UserOrganizationRequest extends AbstractRelationActiveFLaggedRequest<UserOrganization> {

    @Positive(message = "Wrong user id.")
    private Long userId;

    @Positive(message = "Wrong organization id.")
    private Long organizationId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public UserOrganization getEntity() {
        UserOrganization userOrganization = new UserOrganization();
        Organization organization = new Organization();
        User user = new User();

        organization.setId(organizationId);
        user.setId(userId);
        userOrganization.setFirstEntity(user);
        userOrganization.setSecondEntity(organization);
        userOrganization.setActiveFlag(activeFlag);
        userOrganization.setStartDate(startDate);
        userOrganization.setEndDate(endDate);

        return userOrganization;
    }
}
