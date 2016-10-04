package com.yuralubinec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.UserService;
import com.yuralubinec.spring.validator.UserValidator;

@Controller
public class UserController {

    private static final String USER = "user";

    private static final String USER_ITEMS = "items";

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userServiceImpl;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getUserRegistrationPage(Model model) {

        model.addAttribute(USER, new User());
        return "userRegistrationUpdate";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Validated @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "userRegistrationUpdate";
        }

        userServiceImpl.save(user);
        return "redirect:/registration?registrationSuccess=true";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserInfoPage(Model model) {
        model.addAttribute(USER, userServiceImpl
                .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
        return "userRegistrationUpdate";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String editAccount(@Validated @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "userRegistrationUpdate";
        }
        userServiceImpl.update(user);
        return "redirect:/user?updateSuccess=true";
    }

    @RequestMapping(value = "/user/cart", method = RequestMethod.GET)
    public String getUserCart(Model model) {
        User user = userServiceImpl
                .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute(USER, user);
        model.addAttribute(USER_ITEMS, user.getUserItems());
        return "userCart";
    }    
}
