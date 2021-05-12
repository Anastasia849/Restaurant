package ru.mirea.konnova.restaurant.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ru.mirea.konnova.restaurant.model.User;
import ru.mirea.konnova.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/home")
public class UserAccountController {
    private final UserService service;

    public UserAccountController(UserService service) {
        this.service = service;
    }


    @GetMapping("/userAccount")
    public String getUserAccDet(Model model, HttpServletRequest request){
        User currentUser = service.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);

        if(map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
        }

        model.addAttribute("phone", currentUser.getPhone() == null ? "" : currentUser.getPhone());
        model.addAttribute("name", currentUser.getName() == null ? "" : currentUser.getName());
        model.addAttribute("address", currentUser.getAddress() == null ? "" : currentUser.getAddress());
        model.addAttribute("realName", currentUser.getRealName() == null ? "" : currentUser.getRealName());


        return "userAccount";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("phone") String phone,
                             @ModelAttribute("address") String address,
                             @ModelAttribute("realName") String realName,
                             RedirectAttributes redirectAttributes) {
        boolean falseFlag = false;
        User user = service.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());


        if(phone.isEmpty()){
            redirectAttributes.addFlashAttribute("currentPhone", phone);
            redirectAttributes.addFlashAttribute("phoneErr", "Введите ваш номер телефона");
            falseFlag = true;
        }

        if(address.isEmpty()){
            redirectAttributes.addFlashAttribute("currentAddress", address);
            redirectAttributes.addFlashAttribute("addressErr", "Введите адрес");
            falseFlag = true;
        }

        if(realName.isEmpty()){
            redirectAttributes.addFlashAttribute("currentRealName", realName);
            redirectAttributes.addFlashAttribute("realNameErr", "Введите ваше имя");
            falseFlag = true;
        }


        if (falseFlag)
            return "redirect:/home/userAccount";

        user.setAddress(address);
        user.setPhone(phone);
        user.setRealName(realName);

        service.saveOrUpdate(user);

        return "redirect:/home/userAccount";
    }


}
