package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.log.model.entity.ApiLog;
import id.co.bni.ets.lib.log.model.entity.ApiLogId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ApiLogAdminRepository extends PagingAndSortingRepository<ApiLog, ApiLogId> {

    Page<ApiLog> findByUrlContainsIgnoreCase(String searchTerm, Pageable pageable);

    Page<ApiLog> findByApiLogId_DateAfterAndUrlContainsIgnoreCase(Date dateMin, String searchTerm, Pageable pageable);

    Page<ApiLog> findByApiLogId_DateBeforeAndUrlContainsIgnoreCase(Date dateMax, String searchTerm, Pageable pageable);

    Page<ApiLog> findByApiLogId_DateBetweenAndUrlContainsIgnoreCase(Date dateMin, Date dateMax, String searchTerm,
                                                                    Pageable pageable);

    Page<ApiLog> findByApiLogId_UserIdAndUrlContainsIgnoreCase(long userId, String searchTerm, Pageable pageable);

    Page<ApiLog> findByApiLogId_UserIdAndApiLogId_DateAfterAndUrlContainsIgnoreCase(long userId, Date dateMin,
                                                                                    String searchTerm,
                                                                                    Pageable pageable);

    Page<ApiLog> findByApiLogId_UserIdAndApiLogId_DateBeforeAndUrlContainsIgnoreCase(long userId, Date dateMax,
                                                                                     String searchTerm,
                                                                                     Pageable pageable);

    Page<ApiLog> findByApiLogId_UserIdAndApiLogId_DateBetweenAndUrlContainsIgnoreCase(long userId, Date dateMin,
                                                                                      Date dateMax,
                                                                                      String searchTerm,
                                                                                      Pageable pageable);
}
