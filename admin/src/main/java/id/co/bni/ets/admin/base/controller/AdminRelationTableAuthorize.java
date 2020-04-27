package id.co.bni.ets.admin.base.controller;

import id.co.bni.ets.lib.base.controller.table.relation.TableRelationOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('SYS_ADMIN')")
public interface AdminRelationTableAuthorize<T, ID> extends TableRelationOperation<T, ID> {

    @Override
    @PreAuthorize("hasAnyAuthority('READ_ADMIN_ROLE')")
    Page<T> getTable(ID relationId, String searchTerm, Pageable pageable);
}
