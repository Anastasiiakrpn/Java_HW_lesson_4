package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.Role;
import java.util.Optional;

public interface RoleRepository {
    Role create(Role role);
    Optional<Role> findById(Long id);
}
