package ru.mirea.konnova.restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.ElementOfOrder;
import ru.mirea.konnova.restaurant.model.User;
import ru.mirea.konnova.restaurant.service.OrderService;
import ru.mirea.konnova.restaurant.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getMenu(Model model, @AuthenticationPrincipal User user) {
        List<Dish> dishes = orderService.getDishes();
        model.addAttribute("dishes", dishes);

        List<ElementOfOrder> elements = orderService.getCart(user);
        model.addAttribute("elements", elements);

        float total = orderService.getTotal(elements);
        model.addAttribute("total", total);

        return "menu";
    }

    @PostMapping("addElementToOrder/{id}")
    public String addElementToOrder(@PathVariable int id, @AuthenticationPrincipal User user) {
        orderService.addElementToOrder(id, user);
        return "redirect:/home";
    }

    @DeleteMapping("deleteElementFromOrder/{id}")
    public String deleteElementFromOrder(@PathVariable int id) {
        orderService.deleteElementFromOrder(id);
        return "redirect:/home";
    }

    @GetMapping("/orderRegistration")
    public String getOrderRegistration(Model model) {
        User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        List<ElementOfOrder> elements = orderService.getCart(user);
        model.addAttribute("elements", elements);

        float total = orderService.getTotal(elements);
        model.addAttribute("total", total);


        model.addAttribute("errorAddress", (user.getRealName()==null || user.getPhone()==null || user.getAddress()==null) ? "Введите данные для доставки в аккаунте":"");

        model.addAttribute("phone",user.getPhone() == null ? "" : user.getPhone());
        model.addAttribute("realName", user.getRealName() == null ? "" :user.getRealName());
        model.addAttribute("address", user.getAddress() == null ? "" :user.getAddress());

        return "orderRegistration";
    }

    @PostMapping("/orderRegistration/confirm")
    public String confirm(){
        User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        orderService.confirm(user);
        return "confirmation";
    }


//    @GetMapping("/userAccount")
//    public String getUserAccDet(Model model, HttpServletRequest request){
//        User currentUser = service.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
//
//        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
//
//        if(map != null) {
//            for (Map.Entry<String, ?> entry : map.entrySet()) {
//                model.addAttribute(entry.getKey(), entry.getValue());
//            }
//        }
//
//        model.addAttribute("phone", currentUser.getPhone() == null ? "" : currentUser.getPhone());
//        model.addAttribute("name", currentUser.getName() == null ? "" : currentUser.getName());
//        model.addAttribute("address", currentUser.getAddress() == null ? "" : currentUser.getAddress());
//        model.addAttribute("realName", currentUser.getRealName() == null ? "" : currentUser.getRealName());
//
//
//        return "userAccountDetails";
//    }
}
