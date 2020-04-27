package id.co.bni.ets.app.repo;

import id.co.bni.ets.lib.model.entity.RoleMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

@org.springframework.stereotype.Repository
public interface RoleMenuRepository extends Repository<RoleMenu, Long> {

    @Query("select r "
            + "from #{#entityName} r "
            + "where "
            + "     r.firstEntity.id in ?1 "
            + "     and r.secondEntity.moduleId = ?2 "
            + "     and r.secondEntity.parentId is null "
            + "     and r.secondEntity.activeFlag = 'Y' "
            + "     and r.activeFlag = 'Y' "
            + "     and current_date() between r.startDate and r.endDate ")
    Stream<RoleMenu> findParentMenuIsInRoleAndModuleIdEquals(List<Integer> roleIdList, int moduleId);

    @Query("select r "
            + "from #{#entityName} r "
            + "where "
            + "     r.firstEntity.id in ?1 "
            + "     and r.secondEntity.parentId = ?2 "
            + "     and r.secondEntity.moduleId = ?3 "
            + "     and r.secondEntity.activeFlag = 'Y' "
            + "     and r.activeFlag = 'Y' "
            + "     and current_date() between r.startDate and r.endDate ")
    Stream<RoleMenu> findChildMenuIsInRoleAndModuleIdEquals(List<Integer> roleIdList, int parentId, int moduleId);
}
