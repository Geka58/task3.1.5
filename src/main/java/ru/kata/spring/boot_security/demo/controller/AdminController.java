package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    public final UserServiceImpl userServiceImpl;


    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.roleService = roleService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> edit(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDetails> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(userServiceImpl.loadUserByUsername(user.getEmail()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        userService.updateUser(user, id);
        return ResponseEntity.ok(user);
    }
}
