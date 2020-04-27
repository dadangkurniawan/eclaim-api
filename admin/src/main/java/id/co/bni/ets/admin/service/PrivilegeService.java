package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.PrivilegeRepository;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.model.entity.Privilege;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PrivilegeService implements CrudServiceOperation<Privilege, Integer> {

    private final PrivilegeRepository repository;

    public PrivilegeService(PrivilegeRepository repository) {
        this.repository = repository;
    }

    private ResponseStatusException throwNotImplemented() {
        return new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public Privilege findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilege not found."));
    }

    @Override
    public Privilege create(Privilege entity, long userId) {
        throw throwNotImplemented();
    }

    @Override
    public Privilege update(Privilege entity, long userId) {
        throw throwNotImplemented();
    }

    @Override
    public Privilege delete(Integer integer, long userId) {
        throw throwNotImplemented();
    }
}
