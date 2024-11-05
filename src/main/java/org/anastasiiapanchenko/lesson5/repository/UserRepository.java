package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.User;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    User update(User user);

    Optional<User> findById(Long id);
}
