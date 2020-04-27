package id.co.bni.ets.lib.log.repo;

import id.co.bni.ets.lib.log.model.entity.ApiLog;
import id.co.bni.ets.lib.log.model.entity.ApiLogId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends CrudRepository<ApiLog, ApiLogId> {
    // Empty interface.
}
