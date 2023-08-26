package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByEmail(user.getEmail()) == null) {
            if (user.getRoles().isEmpty()) {
                user.setRoles(Collections.singleton(roleRepository.getOne(2)));
            }

            userRepository.save(user);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(User userUpdate, Integer id) {
        User user = userRepository.getById(id);
        user.setUsername(userUpdate.getUsername());
        user.setLastName(userUpdate.getLastName());
        user.setAge(userUpdate.getAge());
        user.setPassword(userUpdate.getPassword());
        if (userRepository.findByEmail(userUpdate.getEmail()) == null) {
            user.setEmail(userUpdate.getEmail());
        }
        if (userUpdate.getRoles().isEmpty()) {
            user.setRoles(Collections.singleton(roleRepository.getOne(2)));
        } else {
            user.setRoles(userUpdate.getRoles());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%S' not find", email));
        }
        return user;
    }

}
