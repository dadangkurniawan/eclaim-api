package id.co.bni.ets.claim.repo;

import id.co.bni.ets.lib.model.entity.DataEntry;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface DataEntryRepository extends PagingAndSortingRepository<DataEntry, Integer> {

    Page<DataEntry> findByNameContainsOrDescriptionContainsAllIgnoreCase(String name, String description, Pageable pageable);

    Stream<DataEntry> findByNameContainsOrDescriptionContainsAllIgnoreCaseOrderByName(String name, String description);
}
