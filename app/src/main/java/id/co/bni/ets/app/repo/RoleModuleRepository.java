package id.co.bni.ets.app.repo;

import id.co.bni.ets.lib.model.entity.RoleModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

@org.springframework.stereotype.Repository
public interface RoleModuleRepository extends Repository<RoleModule, Long> {

    @Query("select r "
            + "from #{#entityName} r "
            + "where "
            + "     r.firstEntity.id in ?1 "
            + "     and r.activeFlag = 'Y' "
            + "     and current_date() between r.startDate and r.endDate ")
    Stream<RoleModule> findDistinctRoleModuleIsInRole(List<Integer> roleIdList);
}
