package id.co.bni.ets.app.controller;

import id.co.bni.ets.app.model.ModuleResponse;
import id.co.bni.ets.app.service.ModuleService;
import id.co.bni.ets.lib.model.ApiResponse;
import id.co.bni.ets.lib.model.AppUserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public ApiResponse<List<ModuleResponse>> getModule(final OAuth2Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<Integer> roleIdList = userDetails.getRoleIdList();

        if (roleIdList == null || roleIdList.isEmpty()) {
            return ApiResponse.apiOk(new ArrayList<>());
        }

        return ApiResponse.apiOk(moduleService.getModuleList(roleIdList));
    }
}
