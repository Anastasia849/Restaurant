package ru.mirea.konnova.restaurant.service;

import ru.mirea.konnova.restaurant.dao.DishDAO;
import ru.mirea.konnova.restaurant.dao.UserDAO;

import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.User;

import java.util.List;

@Service
public class AdminService {

    private final DishDAO dishDAO;
    private final UserDAO userDAO;

    public AdminService(DishDAO dishDAO, UserDAO userDAO) {
        this.dishDAO = dishDAO;
        this.userDAO = userDAO;
    }


    public String addNewPos(Dish dish, Errors errors, RedirectAttributes redirectAttributes){
        boolean flagOfErrors = false;

        if(errors.hasErrors()){
            flagOfErrors = true;

            redirectAttributes.addFlashAttribute("currentTitle", dish.getName());
            redirectAttributes.addFlashAttribute("currentDescription", dish.getDescription());
            redirectAttributes.addFlashAttribute("currentPrice", dish.getPrice());

            List<FieldError> list = errors.getFieldErrors();
            for (FieldError f : list) {
                redirectAttributes.addFlashAttribute(f.getField(), f.getDefaultMessage());
            }
        }

        if(dishDAO.findByName(dish.getName()) != null) {
            flagOfErrors = true;
            redirectAttributes.addFlashAttribute("exists", "Товар с таким названием уже существует");
        }



        if (!flagOfErrors)
            dishDAO.save(dish);

        return "redirect:/admin";
    }

    public String userList(Model model){
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
}
