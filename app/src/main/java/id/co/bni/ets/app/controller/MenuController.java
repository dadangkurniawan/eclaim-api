package id.co.bni.ets.app.controller;

import id.co.bni.ets.app.model.MenuResponse;
import id.co.bni.ets.app.service.MenuService;
import id.co.bni.ets.lib.model.ApiResponse;
import id.co.bni.ets.lib.model.AppUserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/tree/{moduleId:[\\d]+}")
    public ApiResponse<List<MenuResponse>> getMenuTree(@PathVariable int moduleId,
                                                       final OAuth2Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<Integer> roleIdList = userDetails.getRoleIdList();

        if (roleIdList == null || roleIdList.isEmpty()) {
            return ApiResponse.apiOk(new ArrayList<>());
        }

        return ApiResponse.apiOk(menuService.getMenuListByRoleAndModule(roleIdList, moduleId));
    }
}
