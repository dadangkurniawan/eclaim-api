package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.ModuleRepository;
import id.co.bni.ets.lib.base.service.CrudServiceOperation;
import id.co.bni.ets.lib.base.service.SearchServiceOperation;
import id.co.bni.ets.lib.model.entity.Module;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleService implements CrudServiceOperation<Module, Integer>, SearchServiceOperation<Module> {

    private final ModuleRepository repository;

    public ModuleService(ModuleRepository repository) {
        this.repository = repository;
    }

    private ResponseStatusException throwNotImplemented() {
        return new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public Module findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found."));
    }

    @Override
    public Module create(Module entity, long userId) {
        throw throwNotImplemented();
    }

    @Override
    public Module update(Module entity, long userId) {
        throw throwNotImplemented();
    }

    @Override
    public Module delete(Integer integer, long userId) {
        throw throwNotImplemented();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Module> findSuggestions(String searchTerm) {
        return repository
                .findTop20ByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(searchTerm, searchTerm)
                .collect(Collectors.toList());
    }
}
