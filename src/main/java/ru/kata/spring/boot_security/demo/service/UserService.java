package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> listUser();

    void addUser(User user);

    User findByEmail(String email);

    void deleteUser(int id);

    User getUserById(int id);

    void updateUser(User user, Integer id);

}
