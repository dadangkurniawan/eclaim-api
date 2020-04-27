package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Integer> {

    Page<Menu> findByNameContainsOrUrlContainsAllIgnoreCaseOrderByName(String name, String Url, Pageable pageable);

    Stream<Menu> findTop20ByNameContainsAllIgnoreCaseOrderByName(String name);
}
