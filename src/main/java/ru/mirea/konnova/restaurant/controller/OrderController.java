package ru.mirea.konnova.restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.konnova.restaurant.model.Dish;
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
    public String getMenu(Model model){
        List<Dish> dishes=orderService.getDishes();
        model.addAttribute("dishes", dishes);
        return "menu";
    }
    @PostMapping("/addElementToOrder/{name}")
    public void addElementToOrder(@PathVariable String name){
        orderService.addElementToOrder(name);
    }

}
