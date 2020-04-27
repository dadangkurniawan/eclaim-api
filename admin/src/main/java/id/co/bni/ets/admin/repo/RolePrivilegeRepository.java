package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.RolePrivilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings({"SpringDataRepositoryMethodParametersInspection", "SpringDataMethodInconsistencyInspection"})
@Repository
public interface RolePrivilegeRepository extends PagingAndSortingRepository<RolePrivilege, Long> {

    Page<RolePrivilege> findByFirstEntity_Id(int id, Pageable pageable);

    Page<RolePrivilege> findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(int id, String name,
                                                                                   Pageable pageable);

    boolean existsByFirstEntity_IdAndSecondEntity_Id(int roleId, int privilegeId);
}
