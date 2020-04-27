package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.UserOrganizationRepository;
import id.co.bni.ets.lib.base.service.relation.table.AbstractCrudRelationTableService;
import id.co.bni.ets.lib.model.entity.UserOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserOrganizationService extends AbstractCrudRelationTableService<UserOrganization, Long, Long> {

    private final UserOrganizationRepository repository;

    public UserOrganizationService(UserOrganizationRepository userOrganizationRepository, UserService userService,
                                   OrganizationService organizationService) {
        super(userOrganizationRepository, userService, organizationService);
        this.repository = userOrganizationRepository;
    }

    @Override
    public Page<UserOrganization> getTable(Long entityRelationId, Pageable pageable) {
        return repository.findByFirstEntity_Id(entityRelationId, pageable);
    }

    @Override
    public Page<UserOrganization> findTable(Long entityRelationId, String searchTerm, Pageable pageable) {
        return repository.findAllByCustomQuery(entityRelationId, searchTerm, pageable);
    }

    @Override
    public String getFirstEntityNotFoundMessage() {
        return "User not found.";
    }

    @Override
    public String getSecondEntityNotFoundMessage() {
        return "Organization not found.";
    }

    @Override
    public String getNotFoundMessage() {
        return "User Organization not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "User Organization already exists.";
    }
}
