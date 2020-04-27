package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings({"SpringDataRepositoryMethodParametersInspection", "SpringDataMethodInconsistencyInspection"})
@Repository
public interface RoleMenuRepository extends PagingAndSortingRepository<RoleMenu, Long> {

    Page<RoleMenu> findByFirstEntity_Id(int id, Pageable pageable);

    Page<RoleMenu> findByFirstEntity_IdAndSecondEntity_NameContainsIgnoreCase(int id, String name, Pageable pageable);
}
