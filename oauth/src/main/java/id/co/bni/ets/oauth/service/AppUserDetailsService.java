package id.co.bni.ets.oauth.service;

import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.model.AppUserDetails;
import id.co.bni.ets.lib.model.SimpleOrganization;
import id.co.bni.ets.lib.model.entity.*;
import id.co.bni.ets.oauth.repo.UserOrganizationRepository;
import id.co.bni.ets.oauth.repo.UserRepository;
import id.co.bni.ets.oauth.repo.UserRoleRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserOrganizationRepository userOrganizationRepository;

    public AppUserDetailsService(final UserRepository userRepository, final UserRoleRepository userRoleRepository,
                                 final UserOrganizationRepository userOrganizationRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userOrganizationRepository = userOrganizationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        final Collection<UserRole> userRoleCollection = userRoleRepository.findUserRoleByCustomQuery(user)
                .collect(Collectors.toList());
        final Collection<Role> roleCollection = userRoleCollection.stream()
                .map(UserRole::getSecondEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        AppUserDetails appUserDetails = new AppUserDetails(
                org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                        .password(Optional.ofNullable(user.getPassword())
                                          .orElse(""))
                        .disabled(Constant.NO_FLAG == Optional.ofNullable(user.getActiveFlag())
                                .orElse(Constant.NO_FLAG))
                        .accountLocked(Constant.YES_FLAG == Optional.ofNullable(user.getLocked())
                                .orElse(Constant.NO_FLAG))
                        .authorities(getAuthorities(roleCollection))
                        .build());

        appUserDetails.setUserId(user.getId());

        appUserDetails.setRoleIdList(roleCollection.stream()
                                             .map(Role::getId)
                                             .collect(Collectors.toList()));

        appUserDetails.setOrganizationList(userOrganizationRepository.findUserOrganizationByCustomQuery(user)
                                                   .map(UserOrganization::getSecondEntity)
                                                   .filter(Objects::nonNull)
                                                   .map(SimpleOrganization::new)
                                                   .collect(Collectors.toList()));

        return appUserDetails;
    }

    private Collection<GrantedAuthority> getAuthorities(@NonNull Collection<Role> roleCollection) {
        return roleCollection.stream()
                .filter(Objects::nonNull)
                .filter(role -> role.getName() != null)
                .filter(role -> !role.getName()
                        .isEmpty())
                .flatMap(role -> Stream.concat(
                        Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getName())),
                        getPrivileges(role).stream()))
                .collect(Collectors.toList());
    }

    private Collection<GrantedAuthority> getPrivileges(@NonNull Role role) {
        Collection<RolePrivilege> rolePrivilegeCollection = role.getRolePrivileges();
        if (rolePrivilegeCollection == null) {
            return Collections.emptyList();
        }

        return rolePrivilegeCollection.stream()
                .filter(Objects::nonNull)
                .filter(rolePrivilege -> rolePrivilege.getSecondEntity()
                        .getName() != null)
                .filter(rolePrivilege -> !rolePrivilege.getSecondEntity()
                        .getName()
                        .isEmpty())
                .flatMap(rolePrivilege -> {
                    List<GrantedAuthority> privilegeList = new ArrayList<>();
                    String privilegeName = rolePrivilege.getSecondEntity()
                            .getName();
                    if (Constant.YES_FLAG == Character.toUpperCase(rolePrivilege.getCanCreate())) {
                        privilegeList.add(new SimpleGrantedAuthority("CREATE_" + privilegeName));
                    }
                    if (Constant.YES_FLAG == Character.toUpperCase(rolePrivilege.getCanRead())) {
                        privilegeList.add(new SimpleGrantedAuthority("READ_" + privilegeName));
                    }
                    if (Constant.YES_FLAG == Character.toUpperCase(rolePrivilege.getCanUpdate())) {
                        privilegeList.add(new SimpleGrantedAuthority("UPDATE_" + privilegeName));
                    }
                    if (Constant.YES_FLAG == Character.toUpperCase(rolePrivilege.getCanDelete())) {
                        privilegeList.add(new SimpleGrantedAuthority("DELETE_" + privilegeName));
                    }

                    return privilegeList.stream();
                })
                .collect(Collectors.toList());
    }
}
