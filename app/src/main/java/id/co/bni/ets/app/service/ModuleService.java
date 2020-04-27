package id.co.bni.ets.app.service;

import id.co.bni.ets.app.model.ModuleResponse;
import id.co.bni.ets.app.repo.RoleModuleRepository;
import id.co.bni.ets.lib.model.entity.RoleModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ModuleService {

    private final RoleModuleRepository roleModuleRepo;

    public ModuleService(RoleModuleRepository roleModuleRepo) {
        this.roleModuleRepo = roleModuleRepo;
    }

    @Transactional(readOnly = true)
    public List<ModuleResponse> getModuleList(List<Integer> roleIdList) {
        return roleModuleRepo.findDistinctRoleModuleIsInRole(roleIdList)
                .map(RoleModule::getSecondEntity)
                .filter(Objects::nonNull)
                .map(ModuleResponse::new)
                .distinct()
                .collect(Collectors.toList());
    }
}
