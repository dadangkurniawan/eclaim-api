package id.co.bni.ets.claim.base.controller;

import id.co.bni.ets.lib.base.controller.CrudOperation;
import id.co.bni.ets.lib.model.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@PreAuthorize("hasRole('SYS_ADMIN')")
public interface AdminCrudAuthorize<T, ID, R> extends CrudOperation<T, ID, R> {

    @Override
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    ApiResponse<T> read(ID entityId);

    @Override
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN_ROLE')")
    ApiResponse<T> create(R request, final OAuth2Authentication authentication);

    @Override
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN_ROLE')")
    ApiResponse<T> update(R request, ID entityId, final OAuth2Authentication authentication);

    @Override
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN_ROLE')")
    ApiResponse<T> delete(ID entityId, final OAuth2Authentication authentication);
}
