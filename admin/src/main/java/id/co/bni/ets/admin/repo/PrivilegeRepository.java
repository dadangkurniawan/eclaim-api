package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {

    Stream<Privilege> findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(String name, String description);
}
