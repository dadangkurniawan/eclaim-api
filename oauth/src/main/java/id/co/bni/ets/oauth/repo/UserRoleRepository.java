package id.co.bni.ets.oauth.repo;

import id.co.bni.ets.lib.model.entity.User;
import id.co.bni.ets.lib.model.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

@org.springframework.stereotype.Repository
public interface UserRoleRepository extends Repository<UserRole, Long> {

    @Query("select  u "
            + "from #{#entityName} u "
            + "where "
            + "   u.firstEntity = ?1 "
            + "   and u.activeFlag = 'Y' "
            + "   and current_date() between u.startDate and u.endDate "
            + "   order by u.startDate desc")
    Stream<UserRole> findUserRoleByCustomQuery(User user);
}
