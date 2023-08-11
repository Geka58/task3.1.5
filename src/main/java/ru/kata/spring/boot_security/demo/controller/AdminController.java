package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping
    public String userList(ModelMap model) {
        model.addAttribute("user", userService.getAllUser());
        model.addAttribute("role", roleService.getAllRoles());
        return "user_table";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "add_user";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add_user";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, ModelMap model) {
        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("editUser") User user, @PathVariable Integer id,
                             @ModelAttribute("editRoles") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return "/edit_user";
        }
        userService.updateUser(user, id);

        return "redirect:/admin";
    }
}
