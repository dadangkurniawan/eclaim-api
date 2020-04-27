package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.OrganizationRepository;
import id.co.bni.ets.lib.base.service.table.AbstractCrudTableLookupService;
import id.co.bni.ets.lib.model.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService extends AbstractCrudTableLookupService<Organization, Long> {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        super(organizationRepository);
        this.repository = organizationRepository;
    }

    @Override
    public Page<Organization> findTable(String searchTerm, Pageable pageable) {
        return repository.findByNameContainsOrCodeNameContainsOrAbbreviationContainsAllIgnoreCase(searchTerm,
                                                                                                  searchTerm,
                                                                                                  searchTerm, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Organization> findSuggestions(String searchTerm) {
        return repository.findTop20ByNameContainsOrCodeNameContainsOrAbbreviationContainsAllIgnoreCaseOrderByName(
                searchTerm, searchTerm, searchTerm)
                .collect(Collectors.toList());
    }

    @Override
    public String getNotFoundMessage() {
        return "Organization not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Organization already exists.";
    }
}
