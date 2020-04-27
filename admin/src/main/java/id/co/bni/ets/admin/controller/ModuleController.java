package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AdminSearchAuthorize;
import id.co.bni.ets.admin.service.ModuleService;
import id.co.bni.ets.lib.model.ApiResponse;
import id.co.bni.ets.lib.model.entity.Module;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController implements AdminSearchAuthorize<Module> {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    public ApiResponse<Module> read(@PathVariable Integer entityId) {
        return ApiResponse.apiOk(moduleService.findById(entityId));
    }

    @Override
    public ApiResponse<List<Module>> getSuggestions(@RequestParam String searchTerm) {
        return ApiResponse.apiOk(moduleService.findSuggestions(searchTerm));
    }
}
