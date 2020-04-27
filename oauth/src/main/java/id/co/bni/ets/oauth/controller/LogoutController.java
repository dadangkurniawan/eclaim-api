package id.co.bni.ets.oauth.controller;

import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class LogoutController {

    private final DefaultTokenServices tokenServices;

    @Autowired
    public LogoutController(DefaultTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
        }

        return ApiResponse.apiOk("You have been logged out.");
    }
}
