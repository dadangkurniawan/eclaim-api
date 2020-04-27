package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.UserRoleRepository;
import id.co.bni.ets.lib.base.service.relation.table.AbstractCrudRelationTableService;
import id.co.bni.ets.lib.model.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends AbstractCrudRelationTableService<UserRole, Long, Long> {

    private final UserRoleRepository repository;

    public UserRoleService(UserRoleRepository userRoleRepository, UserService userService, RoleService roleService) {
        super(userRoleRepository, userService, roleService);
        this.repository = userRoleRepository;
    }

    @Override
    public Page<UserRole> getTable(Long entityRelationId, Pageable pageable) {
        return repository.findByFirstEntity_Id(entityRelationId, pageable);
    }

    @Override
    public Page<UserRole> findTable(Long entityRelationId, String searchTerm, Pageable pageable) {
        return repository.findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(entityRelationId, searchTerm,
                                                                                     pageable);
    }

    @Override
    public String getFirstEntityNotFoundMessage() {
        return "User not found.";
    }

    @Override
    public String getSecondEntityNotFoundMessage() {
        return "Role not found.";
    }

    @Override
    public String getNotFoundMessage() {
        return "User Role not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "User Role already exists.";
    }
}
