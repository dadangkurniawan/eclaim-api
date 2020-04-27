package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.RolePrivilegeRepository;
import id.co.bni.ets.lib.base.service.relation.AbstractCrudRelationService;
import id.co.bni.ets.lib.base.service.relation.table.TableRelationServiceOperation;
import id.co.bni.ets.lib.model.entity.Privilege;
import id.co.bni.ets.lib.model.entity.Role;
import id.co.bni.ets.lib.model.entity.RolePrivilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolePrivilegeService
        extends AbstractCrudRelationService<RolePrivilege, Long>
        implements TableRelationServiceOperation<RolePrivilege, Integer> {

    private final RolePrivilegeRepository repository;

    public RolePrivilegeService(RolePrivilegeRepository rolePrivilegeRepository, RoleService roleService,
                                PrivilegeService privilegeService) {
        super(rolePrivilegeRepository, roleService, privilegeService);
        this.repository = rolePrivilegeRepository;
    }

    @Override
    public Page<RolePrivilege> getTable(Integer entityRelationId, Pageable pageable) {
        return repository.findByFirstEntity_Id(entityRelationId, pageable);
    }

    @Override
    public Page<RolePrivilege> findTable(Integer entityRelationId, String searchTerm, Pageable pageable) {
        return repository.findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(entityRelationId, searchTerm,
                                                                                     pageable);
    }

    @Override
    public RolePrivilege create(RolePrivilege entity, long userId) {
        Role role = entity.getFirstEntity();
        Privilege privilege = entity.getSecondEntity();

        Optional.ofNullable(role)
                .filter(role1 -> role.getId() != null)
                .orElseThrow(this::throwFirstEntityNotFound);

        Optional.ofNullable(privilege)
                .filter(role1 -> role.getId() != null)
                .orElseThrow(this::throwSecondEntityNotFound);

        if (repository.existsByFirstEntity_IdAndSecondEntity_Id(role.getId(), privilege.getId())) {
            throw throwAlreadyExists();
        }

        return super.create(entity, userId);
    }


    @Override
    public String getFirstEntityNotFoundMessage() {
        return "Role not found.";
    }

    @Override
    public String getSecondEntityNotFoundMessage() {
        return "Privilege not found.";
    }

    @Override
    public String getNotFoundMessage() {
        return "Role Privilege not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Role Privilege already exists.";
    }
}
