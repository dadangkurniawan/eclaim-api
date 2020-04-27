package id.co.bni.ets.oauth.repo;

import id.co.bni.ets.lib.model.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByUsername(String userName);
}
