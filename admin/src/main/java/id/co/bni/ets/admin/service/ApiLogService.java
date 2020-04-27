package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.ApiLogAdminRepository;
import id.co.bni.ets.lib.log.model.entity.ApiLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApiLogService {

    private final ApiLogAdminRepository apiLogAdminRepository;

    public ApiLogService(ApiLogAdminRepository apiLogAdminRepository) {
        this.apiLogAdminRepository = apiLogAdminRepository;
    }

    public Page<ApiLog> getTable(Date dateMin, Date dateMax, @NonNull String searchTerm, Pageable pageable) {
        if (dateMin != null && dateMax != null) {
            return apiLogAdminRepository.findByApiLogId_DateBetweenAndUrlContainsIgnoreCase(dateMin, dateMax,
                                                                                            searchTerm, pageable);
        }

        if (dateMin != null) {
            return apiLogAdminRepository.findByApiLogId_DateAfterAndUrlContainsIgnoreCase(dateMin, searchTerm,
                                                                                          pageable);
        }

        if (dateMax != null) {
            return apiLogAdminRepository.findByApiLogId_DateBeforeAndUrlContainsIgnoreCase(dateMax, searchTerm,
                                                                                           pageable);
        }

        if (!searchTerm.isEmpty()) {
            return apiLogAdminRepository.findByUrlContainsIgnoreCase(searchTerm, pageable);
        }

        return apiLogAdminRepository.findAll(pageable);
    }

    public Page<ApiLog> getTableByUserId(long userId, Date dateMin, Date dateMax, String searchTerm,
                                         Pageable pageable) {
        if (dateMin != null && dateMax != null) {
            return apiLogAdminRepository
                    .findByApiLogId_UserIdAndApiLogId_DateBetweenAndUrlContainsIgnoreCase(userId, dateMin, dateMax,
                                                                                          searchTerm, pageable);
        }

        if (dateMin != null) {
            return apiLogAdminRepository
                    .findByApiLogId_UserIdAndApiLogId_DateAfterAndUrlContainsIgnoreCase(userId, dateMin, searchTerm,
                                                                                        pageable);
        }

        if (dateMax != null) {
            return apiLogAdminRepository
                    .findByApiLogId_UserIdAndApiLogId_DateBeforeAndUrlContainsIgnoreCase(userId, dateMax, searchTerm,
                                                                                         pageable);
        }

        return apiLogAdminRepository
                .findByApiLogId_UserIdAndUrlContainsIgnoreCase(userId, searchTerm, pageable);
    }
}
