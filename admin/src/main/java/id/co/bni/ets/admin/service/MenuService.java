package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.MenuRepository;
import id.co.bni.ets.lib.base.service.table.AbstractCrudTableLookupService;
import id.co.bni.ets.lib.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService extends AbstractCrudTableLookupService<Menu, Integer> {

    private final MenuRepository repository;

    public MenuService(MenuRepository menuRepository) {
        super(menuRepository);
        this.repository = menuRepository;
    }

    @Override
    public Page<Menu> findTable(String searchTerm, Pageable pageable) {
        return repository.findByNameContainsOrUrlContainsAllIgnoreCaseOrderByName(searchTerm, searchTerm, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Menu> findSuggestions(String searchTerm) {
        return repository.findTop20ByNameContainsAllIgnoreCaseOrderByName(searchTerm)
                .collect(Collectors.toList());
    }

    @Override
    public String getNotFoundMessage() {
        return "Menu not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Menu already exists.";
    }
}
