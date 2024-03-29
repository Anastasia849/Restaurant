package ru.mirea.konnova.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.konnova.restaurant.dao.DishDAO;
import ru.mirea.konnova.restaurant.dao.ShoppingCartDAO;
import ru.mirea.konnova.restaurant.dao.UserDAO;

import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.ShoppingCart;
import ru.mirea.konnova.restaurant.model.Status;
import ru.mirea.konnova.restaurant.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    private final DishDAO dishDAO;
    private final UserDAO userDAO;
    private final ShoppingCartDAO shoppingCartDAO;

    @Autowired
    public AdminService(DishDAO dishDAO, UserDAO userDAO, ShoppingCartDAO shoppingCartDAO) {
        this.dishDAO = dishDAO;
        this.userDAO = userDAO;
        this.shoppingCartDAO = shoppingCartDAO;
    }


    public String addDish(Dish dish, Errors errors, RedirectAttributes redirectAttributes){
        boolean flagOfErrors = false;


        if(errors.hasErrors()){
            flagOfErrors = true;

            redirectAttributes.addFlashAttribute("currentName", dish.getName());
            redirectAttributes.addFlashAttribute("currentDescription", dish.getDescription());
            redirectAttributes.addFlashAttribute("currentPrice", dish.getPrice());

            List<FieldError> list = errors.getFieldErrors();
            for (FieldError f : list) {
                redirectAttributes.addFlashAttribute(f.getField(), f.getDefaultMessage());
            }
        }

        if(dishDAO.findByName(dish.getName()) != null) {
            flagOfErrors = true;
            redirectAttributes.addFlashAttribute("exists", "Блюдо с таким названием уже существует");
        }



        if (!flagOfErrors)
            dishDAO.save(dish);

        return "redirect:/admin";
    }

    public void deleteDish(int id){
        dishDAO.deleteById(id);
    }

    public String userList(Model model){
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    public List<ShoppingCart> getProcessingOrders(){
        return shoppingCartDAO.findAllByStatuses(Status.ORDER_PROCESSING);
    }

    public List<ShoppingCart> getProcessedOrders(){
        return shoppingCartDAO.findAllByStatuses(Status.ORDER_PROCESSED);
    }

    public void process(int id){
        ShoppingCart shoppingCart = shoppingCartDAO.findById(id);
        shoppingCart.setStatuses(Collections.singleton(Status.ORDER_PROCESSED));
        shoppingCartDAO.save(shoppingCart);
    }
}
