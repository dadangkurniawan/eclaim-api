package id.co.bni.ets.lib.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@SuppressWarnings("unused")
public class AppUserDetails extends User {

    private Long userId;

    private List<SimpleOrganization> organizationList;

    private List<Integer> roleIdList;

    public AppUserDetails(UserDetails userDetails) {
        super(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(),
              userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(),
              userDetails.isAccountNonLocked(), userDetails.getAuthorities());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SimpleOrganization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<SimpleOrganization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
