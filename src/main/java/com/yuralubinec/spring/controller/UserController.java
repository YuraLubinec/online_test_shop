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

/**
 * Handles and retrieves user-account page depending on the URI template. A user must be log-in first he can access
 * this page.
 * 
 */

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

    /**
     * Retrieves user registration page.
     * 
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getUserRegistrationPage(Model model) {

        model.addAttribute(USER, new User());
        return "userRegistrationUpdate";
    }

    /**
     * Handles user registration and saves it to the database. In case of validation errors forwards
     * user to user registration page and shows validation result's messages, otherwise show success massage 
     * on current page.
     * 
     * @param user {@link User} instance should be updated
     * @param result BindingResult instance for result of validation
     * @return name of view will be returned
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Validated @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "userRegistrationUpdate";
        }

        userServiceImpl.save(user);
        return "redirect:/registration?registrationSuccess=true";
    }

    /**
     * Retrieves user update page.
     * 
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserInfoPage(Model model) {
        
        model.addAttribute(USER, userServiceImpl
                .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())));
        return "userRegistrationUpdate";
    }

    /**
     * Handles changes of user-account information and saves it to the database. In case of validation errors forwards
     * user to user update page and shows validation result's messages, otherwise show success massage 
     * on current page.
     * 
     * @param user {@link User} instance should be updated
     * @param result BindingResult instance for result of validation
     * @return name of view will be returned
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String editAccount(@Validated @ModelAttribute User user, BindingResult result) {

        if (result.hasErrors()) {
            return "userRegistrationUpdate";
        }
        userServiceImpl.update(user);
        return "redirect:/user?updateSuccess=true";
    }
  
    /**
     * Retrieves user cart page
     * 
     * @param model {@link Model} object
     * @return name of view will be returned
     */
    @RequestMapping(value = "/user/cart", method = RequestMethod.GET)
    public String getUserCart(Model model) {
       
        User user = userServiceImpl
                .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute(USER, user);
        model.addAttribute(USER_ITEMS, user.getUserItems());
        return "userCart";
    }    
}
