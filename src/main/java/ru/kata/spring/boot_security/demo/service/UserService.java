package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUser();

    void addUser(User user);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    void deleteUser(int id);

    User getUserById(int id);

    void updateUser(User user, Integer id);

}
