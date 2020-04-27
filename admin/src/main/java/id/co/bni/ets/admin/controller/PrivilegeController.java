package id.co.bni.ets.admin.controller;

import id.co.bni.ets.admin.base.controller.AdminSearchAuthorize;
import id.co.bni.ets.admin.repo.PrivilegeRepository;
import id.co.bni.ets.lib.model.ApiResponse;
import id.co.bni.ets.lib.model.entity.Privilege;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController implements AdminSearchAuthorize<Privilege> {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeController(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<Privilege>> getSuggestions(@RequestParam String searchTerm) {
        List<Privilege> privilegeList = privilegeRepository
                .findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(searchTerm, searchTerm)
                .collect(Collectors.toList());

        return ApiResponse.apiOk(privilegeList);
    }
}
