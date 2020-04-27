package id.co.bni.ets.oauth.config;

import id.co.bni.ets.lib.model.entity.User;
import id.co.bni.ets.oauth.repo.UserRepository;
import id.co.bni.ets.oauth.service.AppUserDetailsService;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.util.Collection;

public class LdapContextMapper implements UserDetailsContextMapper {

    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;

    LdapContextMapper(UserRepository userRepository, AppUserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                          Collection<? extends GrantedAuthority> authorities) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return appUserDetailsService.loadUserByUsername(user.getUsername());
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        throw new UnsupportedOperationException("LdapUserDetailsMapper only supports reading from a context. Please use"
                + " a subclass if mapUserToContext() is required.");
    }
}
