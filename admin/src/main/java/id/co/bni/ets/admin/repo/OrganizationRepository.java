package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

    Page<Organization> findByNameContainsOrCodeNameContainsOrAbbreviationContainsAllIgnoreCase(String name,
                                                                                               String codeName,
                                                                                               String abbreviation,
                                                                                               Pageable pageable);

    Stream<Organization> findTop20ByNameContainsOrCodeNameContainsOrAbbreviationContainsAllIgnoreCaseOrderByName(
            String name, String codeName, String abbreviation);
}
