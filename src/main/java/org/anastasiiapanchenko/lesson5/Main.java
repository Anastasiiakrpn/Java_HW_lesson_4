package org.anastasiiapanchenko.lesson5;

import org.anastasiiapanchenko.lesson5.model.Role;
import org.anastasiiapanchenko.lesson5.model.User;
import org.anastasiiapanchenko.lesson5.repository.UserRepositoryImpl;
import org.anastasiiapanchenko.lesson5.repository.RoleRepositoryImpl;

import java.util.Arrays;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();

        Role adminRole = new Role("admin");
        roleRepository.create(adminRole);

        Role userRole = new Role("user");
        roleRepository.create(userRole);

        User user = new User();
        user.setName("Baloo Pes");
        user.setEmail("pes.baloo@example.com");
        user.setPassword("123");
        user.setRoles(Arrays.asList(adminRole, userRole));
        userRepository.create(user);

        userRepository.findById(user.getId()).ifPresent(value ->
                System.out.println("User: " + value.getName() + ", Roles: " + value.getRoles())
        );

        user.setName("Amour Kit");
        userRepository.update(user);

        userRepository.findById(user.getId()).ifPresent(updatedUser ->
                System.out.println("Updated User: " + updatedUser.getName())
        );
    }
}
