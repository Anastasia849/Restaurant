package ru.mirea.konnova.restaurant.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.konnova.restaurant.dao.UserDAO;
import ru.mirea.konnova.restaurant.model.Role;
import ru.mirea.konnova.restaurant.model.User;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder encoder;
    private final UserDetailsService detailsService;

    public UserService(UserDAO userDAO, BCryptPasswordEncoder encoder, UserDetailsService detailsService) {
        this.userDAO = userDAO;
        this.encoder = encoder;
        this.detailsService = detailsService;
    }


    public String addUser(@ModelAttribute @Valid User user, Errors errors, @RequestParam Role role, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("currentName",user.getName());
            model.addAttribute("currentPassword",user.getPassword());

            List<FieldError> list = errors.getFieldErrors();
            for (FieldError f : list) {
                model.addAttribute(f.getField(), f.getDefaultMessage());
            }

            if (detailsService.loadUserByUsername(user.getName()) != null) {
                model.addAttribute("errorAcc", "Пользователь с таким именем уже существует.");
            }
            return "/registration";
        }

        user.setRoles(Collections.singleton(role));
        user.setPassword(encode(user.getPassword()));
        user.setActive(true);
        userDAO.save(user);

        return "redirect:/login";
    }

    public void saveOrUpdate(User user){
        userDAO.save(user);
    }

    public User getUserByName(String name){
        return userDAO.findByName(name);
    }

    public boolean equalsPassword(String pas1, String pas2){
        return encoder.matches(pas1, pas2);
    }

    public String encode(String pass){
        return encoder.encode(pass);
    }

    public boolean matches(String password, String currentPassword){
        return encoder.matches(password, currentPassword);
    }

}
