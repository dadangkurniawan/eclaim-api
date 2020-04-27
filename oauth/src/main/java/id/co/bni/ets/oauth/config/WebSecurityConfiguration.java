package id.co.bni.ets.oauth.config;

import id.co.bni.ets.oauth.repo.UserOrganizationRepository;
import id.co.bni.ets.oauth.repo.UserRepository;
import id.co.bni.ets.oauth.repo.UserRoleRepository;
import id.co.bni.ets.oauth.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserOrganizationRepository userOrganizationRepository;
    private final LdapContextSource ldapContextSource;

    private PasswordEncoder passwordEncoder;

    public WebSecurityConfiguration(final UserRepository userRepository, final UserRoleRepository userRoleRepository,
                                    final UserOrganizationRepository userOrganizationRepository,
                                    final LdapContextSource ldapContextSource) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userOrganizationRepository = userOrganizationRepository;
        this.ldapContextSource = ldapContextSource;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AppAuthenticationProvider(userRepository, getDaoAuthenticationProvider(),
                                                                  getLdapAuthenticationProvider()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        return passwordEncoder;
    }

    @Bean
    public AppUserDetailsService getUserDetailsService() {
        return new AppUserDetailsService(userRepository, userRoleRepository, userOrganizationRepository);
    }

    @Bean
    public UserDetailsContextMapper getLdapContextMapper() {
        return new LdapContextMapper(userRepository, getUserDetailsService());
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(passwordEncoder());
        daoProvider.setUserDetailsService(getUserDetailsService());

        return daoProvider;
    }

    @Bean
    public LdapAuthenticationProvider getLdapAuthenticationProvider() {
        BindAuthenticator bindAuthenticator = new BindAuthenticator(ldapContextSource);
        bindAuthenticator.setUserDnPatterns(new String[]{"uid={0}"});
        LdapAuthenticationProvider authProvider = new LdapAuthenticationProvider(bindAuthenticator);
        authProvider.setUserDetailsContextMapper(getLdapContextMapper());

        return authProvider;
    }
}
