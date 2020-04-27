package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.RoleRepository;
import id.co.bni.ets.lib.base.service.table.AbstractCrudTableLookupService;
import id.co.bni.ets.lib.model.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService extends AbstractCrudTableLookupService<Role, Integer> {

    private final RoleRepository repository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.repository = roleRepository;
    }

    @Override
    public Page<Role> findTable(String searchTerm, Pageable pageable) {
        return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(searchTerm, searchTerm, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findSuggestions(String searchTerm) {
        return repository.findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(searchTerm, searchTerm)
                .collect(Collectors.toList());
    }

    @Override
    public Role create(Role role, long userId) {
        Optional.ofNullable(role.getRoleMenus())
                .ifPresent(roleMenus -> roleMenus.forEach(roleMenu -> {
                    roleMenu.setInitTrack(userId);
                    roleMenu.setFirstEntity(role);
                }));
        Optional.ofNullable(role.getRolePrivileges())
                .ifPresent(rolePrivileges -> rolePrivileges.forEach(rolePrivilege -> {
                    rolePrivilege.setInitTrack(userId);
                    rolePrivilege.setFirstEntity(role);
                }));

        return getRepository().save(role);
    }

    @Override
    public String getNotFoundMessage() {
        return "Role not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Role already exists.";
    }
}
