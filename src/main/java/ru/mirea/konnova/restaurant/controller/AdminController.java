package ru.mirea.konnova.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.ShoppingCart;
import ru.mirea.konnova.restaurant.model.User;
import ru.mirea.konnova.restaurant.service.AdminService;
import ru.mirea.konnova.restaurant.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final OrderService orderService;

    @Autowired
    public AdminController(AdminService service, OrderService orderService) {
        this.adminService = service;
        this.orderService = orderService;
    }


    @GetMapping("")
    String getAdminPage(Model model){
        List<Dish> dishes=orderService.getDishes();
        model.addAttribute("dishes", dishes);
        return "admin";
    }
    @DeleteMapping("/deleteDish/{id}")
    public String deleteDish(@PathVariable("id") int id){
        adminService.deleteDish(id);
        return "redirect:/admin";
    }

    @PostMapping("/addDish")
    public String addDish(@ModelAttribute @Valid Dish dish, Errors errors, RedirectAttributes redirectAttributes){
        return  adminService.addDish(dish, errors, redirectAttributes);
    }

    @GetMapping("/userList")
    public String getUserList(Model model){
        return adminService.userList(model);
    }

    @GetMapping("/orderList")
    public String getOrders(Model model){
        model.addAttribute("ordersProcessing",adminService.getProcessingOrders());
        model.addAttribute("ordersProcessed",adminService.getProcessedOrders());
        return "orderList";
    }
    @PostMapping("/orderList/process/{id}")
    public String process(@PathVariable("id") int id){
        adminService.process(id);
        return "redirect:/admin/orderList";
    }

}