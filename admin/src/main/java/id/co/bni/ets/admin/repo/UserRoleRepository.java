package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings({"SpringDataRepositoryMethodParametersInspection", "SpringDataMethodInconsistencyInspection"})
@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {

    Page<UserRole> findByFirstEntity_Id(long id, Pageable pageable);

    Page<UserRole> findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(long userId, String name,
                                                                              Pageable pageable);
}
