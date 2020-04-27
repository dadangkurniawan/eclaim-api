package id.co.bni.ets.oauth.config;

import id.co.bni.ets.lib.model.entity.User;
import id.co.bni.ets.oauth.repo.UserRepository;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

    private static final String LDAP_FLAG = "LDAP";

    private final UserRepository appUserRepository;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final LdapAuthenticationProvider ldapAuthenticationProvider;

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public AppAuthenticationProvider(UserRepository appUserRepository,
                                     DaoAuthenticationProvider daoAuthenticationProvider,
                                     LdapAuthenticationProvider ldapAuthenticationProvider) {
        this.appUserRepository = appUserRepository;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.ldapAuthenticationProvider = ldapAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final User user = appUserRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials")));

        if (LDAP_FLAG.equals(user.getAuthProvider())) {
            return ldapAuthenticationProvider.authenticate(authentication);
        }

        return daoAuthenticationProvider.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
