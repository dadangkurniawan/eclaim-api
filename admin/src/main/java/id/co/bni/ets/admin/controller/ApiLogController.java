package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.service.ApiLogService;
import id.co.bni.ets.lib.log.model.entity.ApiLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api-log")
public class ApiLogController {

    private final ApiLogService apiLogService;

    public ApiLogController(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    public Page<ApiLog> getTable(@RequestParam(required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateMin,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateMax,
                                 @RequestParam(required = false, defaultValue = "") String searchTerm,
                                 @PageableDefault(5) Pageable pageable) {
        return apiLogService.getTable(dateMin, dateMax, searchTerm, pageable);
    }

    @GetMapping("/userId/{userId:[\\d]+}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    public Page<ApiLog> getTableByUserAndDate(@PathVariable long userId,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateMin,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateMax,
                                              @RequestParam(required = false, defaultValue = "") String searchTerm,
                                              @PageableDefault(5) Pageable pageable) {
        return apiLogService.getTableByUserId(userId, dateMin, dateMax, searchTerm, pageable);
    }
}
