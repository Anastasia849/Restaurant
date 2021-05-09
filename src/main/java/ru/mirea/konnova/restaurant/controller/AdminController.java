package ru.mirea.konnova.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

//    @GetMapping("")
//    public String adminPage(Model model, HttpServletRequest request) {
//        ProductType[] productType = ProductType.values();
//        model.addAttribute("prods", productType);
//        model.addAttribute("prodss", productType);
//
//        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
//        if(map != null) {
//            for (Map.Entry<String, ?> entry : map.entrySet()) {
//                model.addAttribute(entry.getKey(), entry.getValue());
//            }
//        }
//
//        return "adminPage";
//    }

    @PostMapping("/addNewPosition")
    public String addNewPosition(@ModelAttribute @Valid Dish dish, Errors errors, RedirectAttributes redirectAttributes){
        return  service.addNewPos(dish, errors, redirectAttributes);
    }

    @GetMapping("/userList")
    public String getUserList(Model model){
        return service.userList(model);
    }
}