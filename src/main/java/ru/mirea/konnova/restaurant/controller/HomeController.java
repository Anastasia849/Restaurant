package ru.mirea.konnova.restaurant.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.konnova.restaurant.model.Role;
import ru.mirea.konnova.restaurant.model.User;
import ru.mirea.konnova.restaurant.service.UserService;

import javax.validation.Valid;

@Controller
public class HomeController {
    private final UserService service;

    public HomeController(UserService service) {
        this.service = service;
    }


    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid User user, Errors errors, @RequestParam Role role, Model model) {
        return service.addUser(user, errors, role, model);
    }

    @GetMapping("")
    public String afterLogin(@AuthenticationPrincipal User user){
        if(user.getRoles().contains(Role.USER)){
            return "redirect:/home";
        } else {
            return "redirect:/admin";
        }
    }
}