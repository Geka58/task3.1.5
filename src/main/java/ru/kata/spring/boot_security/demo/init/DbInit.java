package ru.kata.spring.boot_security.demo.init;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public DbInit(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role(1, "ADMIN");
        Role roleUser = new Role(2, "USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        User user = new User("user", "userov", 20, "100", "user@mail.ru", Set.of(roleUser));
        User admin = new User("admin", "adminov", 20, "100", "admin@mail.ru", Set.of(roleAdmin, roleUser));
        userService.addUser(user);
        userService.addUser(admin);
    }
}
