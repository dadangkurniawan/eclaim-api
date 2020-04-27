package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.UserOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
@Repository
public interface UserOrganizationRepository extends PagingAndSortingRepository<UserOrganization, Long> {

    Page<UserOrganization> findByFirstEntity_Id(long id, Pageable pageable);

    @Query("select u "
            + "from #{#entityName} u "
            + "where "
            + "     u.firstEntity.id = :userId "
            + "     and ("
            + "         UPPER(u.secondEntity.name) like CONCAT('%',UPPER(:searchTerm),'%') "
            + "         or UPPER(u.secondEntity.codeName) like CONCAT('%',UPPER(:searchTerm),'%') "
            + "         or UPPER(u.secondEntity.abbreviation) like CONCAT('%',UPPER(:searchTerm),'%')"
            + "     )")
    Page<UserOrganization> findAllByCustomQuery(@Param("userId") long userId, @Param("searchTerm") String searchTerm,
                                                Pageable pageable);
}
