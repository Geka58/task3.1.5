package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/";
        } else if (userService.findByEmail(user.getEmail()) == null) {
            user.setRoles(Collections.singleton(roleRepository.getOne(1)));
            userService.addUser(user);
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String registration() {
        return "main_page";
    }
}
