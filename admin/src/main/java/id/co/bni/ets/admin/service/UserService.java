package id.co.bni.ets.admin.service;

import id.co.bni.ets.admin.repo.UserRepository;
import id.co.bni.ets.lib.Constant;
import id.co.bni.ets.lib.base.service.table.AbstractCrudTableService;
import id.co.bni.ets.lib.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService extends AbstractCrudTableService<User, Long> {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<User> findTable(String searchTerm, Pageable pageable) {
        return repository.findByUsernameContainsOrDescriptionContainsAllIgnoreCase(searchTerm, searchTerm, pageable);
    }

    @Override
    public User create(User user, long userId) {
        if (Constant.LOCAL_FLAG.equals(user.getAuthProvider())) {
            if (StringUtils.isEmpty(user.getPassword())) {
                // If new user with null or empty password then throw an error.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be null on LOCAL type.");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(null);
        }

        Optional.ofNullable(user.getUserRoles())
                .ifPresent(userRoles -> userRoles.forEach(userRole -> {
                    userRole.setInitTrack(userId);
                    userRole.setFirstEntity(user);
                }));
        Optional.ofNullable(user.getUserOrganizations())
                .ifPresent(userOrganizations -> userOrganizations.forEach(userOrganization -> {
                    userOrganization.setInitTrack(userId);
                    userOrganization.setFirstEntity(user);
                }));

        return getRepository().save(user);
    }

    @Override
    public User update(User user, long userId) {
        User originalUser = getRepository().findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        user.prepareUpdate(originalUser, userId);

        if (!Constant.LOCAL_FLAG.equals(user.getAuthProvider())) {
            user.setPassword(null);
        } else if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(originalUser.getPassword());
        }

        return getRepository().save(user);
    }

    @Override
    public String getNotFoundMessage() {
        return "User not found.";
    }

    @Override
    public String getAlreadyExistsMessage() {
        return "User already exists.";
    }
}
