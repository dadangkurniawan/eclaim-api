package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    Page<Role> findByNameContainsOrDescriptionContainsAllIgnoreCase(String name, String description, Pageable pageable);

    Stream<Role> findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(String name, String description);
}