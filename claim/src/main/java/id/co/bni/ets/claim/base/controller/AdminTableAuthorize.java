package id.co.bni.ets.claim.base.controller;

import id.co.bni.ets.lib.base.controller.table.TableOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('SYS_ADMIN')")
public interface AdminTableAuthorize<T> extends TableOperation<T> {

    @Override
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    Page<T> getTable(String searchTerm, Pageable pageable);
}
