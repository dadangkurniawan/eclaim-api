package id.co.bni.ets.claim.service;

import id.co.bni.ets.claim.repo.DataEntryRepository;
import id.co.bni.ets.lib.base.service.table.AbstractCrudTableLookupService;
import id.co.bni.ets.lib.model.entity.DataEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataEntryService extends AbstractCrudTableLookupService<DataEntry, Integer> {

    private final DataEntryRepository repository;

    public DataEntryService(DataEntryRepository dataEntryRepository) {
        super(dataEntryRepository);
        this.repository = dataEntryRepository;
    }

    @Override
    public Page<DataEntry> findTable(String searchTerm, Pageable pageable) {
        return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(searchTerm, searchTerm, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DataEntry> findSuggestions(String searchTerm) {
        return repository.findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(searchTerm, searchTerm)
                .collect(Collectors.toList());
    }

    @Override
    public DataEntry create(DataEntry dataEntry, long userId) {
        return getRepository().save(dataEntry);
    }
    
    @Override
    public String getNotFoundMessage() {
        return "Data not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "Data already exists.";
    }
}
