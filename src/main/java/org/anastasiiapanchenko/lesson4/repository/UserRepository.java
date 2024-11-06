package org.anastasiiapanchenko.lesson4.repository;

import org.anastasiiapanchenko.lesson4.model.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
