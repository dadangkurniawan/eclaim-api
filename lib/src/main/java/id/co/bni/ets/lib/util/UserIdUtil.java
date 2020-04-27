package id.co.bni.ets.lib.util;

import id.co.bni.ets.lib.model.AppUserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class UserIdUtil {

    private static final Logger logger = LogManager.getLogger(UserIdUtil.class);

    public static Long getUserId(final OAuth2Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        logger.debug("User ID = " + userDetails.getUserId());
        return userDetails.getUserId();
    }
}
