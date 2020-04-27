package id.co.bni.ets.admin.base.controller;

import id.co.bni.ets.lib.base.controller.SearchOperation;
import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('SYS_ADMIN')")
public interface AdminSearchAuthorize<T> extends SearchOperation<T> {

    @Override
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    ApiResponse<List<T>> getSuggestions(String searchTerm);
}
