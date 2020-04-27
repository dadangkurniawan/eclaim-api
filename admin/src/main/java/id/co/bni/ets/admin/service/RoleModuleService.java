package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.RoleModuleRepository;
import id.co.bni.ets.lib.base.service.relation.table.AbstractCrudRelationTableService;
import id.co.bni.ets.lib.model.entity.RoleModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleModuleService extends AbstractCrudRelationTableService<RoleModule, Long, Integer> {

    private final RoleModuleRepository repository;

    public RoleModuleService(RoleModuleRepository repository, RoleService roleService, ModuleService moduleService) {
        super(repository, roleService, moduleService);
        this.repository = repository;
    }

    @Override
    public Page<RoleModule> getTable(Integer entityRelationId, Pageable pageable) {
        return repository.findByFirstEntity_Id(entityRelationId, pageable);
    }

    @Override
    public Page<RoleModule> findTable(Integer entityRelationId, String searchTerm, Pageable pageable) {
        return repository.findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(entityRelationId, searchTerm,
                                                                                     pageable);
    }

    @Override
    public String getFirstEntityNotFoundMessage() {
        return "Role not found.";
    }

    @Override
    public String getSecondEntityNotFoundMessage() {
        return "Module not found.";
    }

    @Override
    public String getNotFoundMessage() {
        return "Role Module not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Role Module already exists.";
    }
}
