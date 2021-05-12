package ru.mirea.konnova.restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.ElementOfOrder;
import ru.mirea.konnova.restaurant.model.User;
import ru.mirea.konnova.restaurant.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String getMenu(Model model,@AuthenticationPrincipal User user){
        List<Dish> dishes=orderService.getDishes();
        model.addAttribute("dishes", dishes);

        List<ElementOfOrder> elements =orderService.getCart(user);
        model.addAttribute("elements",elements);
        return "menu";
    }
    @PostMapping("addElementToOrder/{id}")
    public String addElementToOrder(@PathVariable int id, @AuthenticationPrincipal User user){
        orderService.addElementToOrder(id,user);
        return "redirect:/home";
    }

    @DeleteMapping("deleteElementFromOrder/{id}")
    public String deleteElementFromOrder(@PathVariable int id){
        orderService.deleteElementFromOrder(id);
        return "redirect:/home";
    }
}
