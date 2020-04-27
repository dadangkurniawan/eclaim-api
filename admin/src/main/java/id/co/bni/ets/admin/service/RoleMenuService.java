package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.RoleMenuRepository;
import id.co.bni.ets.lib.base.service.relation.table.AbstractCrudRelationTableService;
import id.co.bni.ets.lib.model.entity.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuService extends AbstractCrudRelationTableService<RoleMenu, Long, Integer> {

    private final RoleMenuRepository repository;

    public RoleMenuService(RoleMenuRepository roleMenuRepository, RoleService roleService, MenuService menuService) {
        super(roleMenuRepository, roleService, menuService);
        this.repository = roleMenuRepository;
    }

    @Override
    public Page<RoleMenu> getTable(Integer entityRelationId, Pageable pageable) {
        return repository.findByFirstEntity_Id(entityRelationId, pageable);
    }

    @Override
    public Page<RoleMenu> findTable(Integer entityRelationId, String searchTerm, Pageable pageable) {
        return repository.findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(entityRelationId, searchTerm,
                                                                                     pageable);
    }

    @Override
    public String getFirstEntityNotFoundMessage() {
        return "Role not found.";
    }

    @Override
    public String getSecondEntityNotFoundMessage() {
        return "Menu not found.";
    }

    @Override
    public String getNotFoundMessage() {
        return "Role Menu not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Role Menu already exists.";
    }
}
