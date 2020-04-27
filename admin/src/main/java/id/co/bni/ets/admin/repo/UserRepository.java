package id.co.bni.ets.admin.repo;

import id.co.bni.ets.lib.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findByUsernameContainsOrDescriptionContainsAllIgnoreCase(String username, String description,
                                                                        Pageable pageable);
}