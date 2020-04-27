package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Integer> {

    Stream<Module> findTop20ByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(String name, 
            String description);
}
