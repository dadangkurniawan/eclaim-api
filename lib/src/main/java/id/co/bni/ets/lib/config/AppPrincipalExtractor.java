package id.co.bni.ets.lib.config;

import id.co.bni.ets.lib.model.AppUserDetails;
import id.co.bni.ets.lib.model.SimpleOrganization;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class AppPrincipalExtractor implements PrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        Map<String, Object> userAuthenticationMap = (Map<String, Object>) map.get("principal");
        String username = (String) userAuthenticationMap.get("username");
        Boolean isEnabled = (Boolean) userAuthenticationMap.get("enabled");
        Boolean isAccountNonExpired = (Boolean) userAuthenticationMap.get("accountNonExpired");
        Boolean isCredentialsNonExpired = (Boolean) userAuthenticationMap.get("credentialsNonExpired");
        Boolean isAccountNonLocked = (Boolean) userAuthenticationMap.get("accountNonLocked");
        Long userId = Long.valueOf((Integer) userAuthenticationMap.get("userId"));
        List<Integer> roleIdCollection = (List<Integer>) userAuthenticationMap.get("roleIdList");

        AppUserDetails appUserDetails = new AppUserDetails(
                User.withUsername(username)
                        .password("")
                        .disabled(!isEnabled)
                        .accountExpired(!isAccountNonExpired)
                        .credentialsExpired(!isCredentialsNonExpired)
                        .accountLocked(!isAccountNonLocked)
                        .authorities(getGrantedAuthorities(userAuthenticationMap))
                        .build());

        appUserDetails.setUserId(userId);
        appUserDetails.setRoleIdList(roleIdCollection);
        appUserDetails.setOrganizationList(getOrganizationList(userAuthenticationMap));

        return appUserDetails;
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(@NonNull Map<String, Object> userAuthenticationMap) {
        List<Map<String, Object>> grantedAuthoritiesMap =
                (List<Map<String, Object>>) userAuthenticationMap.get("authorities");
        if (grantedAuthoritiesMap == null) {
            return Collections.emptyList();
        }

        return grantedAuthoritiesMap.stream()
                .map(grantedAuthority -> new SimpleGrantedAuthority((String) grantedAuthority.get("authority")))
                .collect(Collectors.toList());
    }

    private List<SimpleOrganization> getOrganizationList(@NonNull Map<String, Object> userAuthenticationMap) {
        List<Map<String, Object>> organizationListMap =
                (List<Map<String, Object>>) userAuthenticationMap.get("organizationList");
        if (organizationListMap == null) {
            return Collections.emptyList();
        }

        return organizationListMap.stream()
                .map(organizationMap -> {
                    SimpleOrganization organization = new SimpleOrganization();
                    organization.setId(Long.valueOf((Integer) organizationMap.get("id")));
                    organization.setName((String) organizationMap.get("name"));
                    organization.setDescription((String) organizationMap.get("description"));
                    organization.setCodeName((String) organizationMap.get("codeName"));
                    organization.setAbbreviation((String) organizationMap.get("abbreviation"));

                    return organization;
                })
                .collect(Collectors.toList());
    }
}
