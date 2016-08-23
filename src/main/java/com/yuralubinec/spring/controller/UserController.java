package com.yuralubinec.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yuralubinec.spring.editor.ItemEditor;
import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.UserService;
import com.yuralubinec.spring.validator.UserValidator;

@Controller
public class UserController {
    
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

	private static final String USER = "user"; 
    
    @Autowired
    ItemEditor itemEditor;
    
    @Autowired
    UserValidator userValidator;
    
    @Autowired 
    UserService userServiceImpl;
    
       
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "userItems", itemEditor);
        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getUserAccountPage(Model model) {
    	model.addAttribute(USER, new User());
    	System.out.println("yeah");
        return "userRegistration";
    }

   
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String editAccount(@Validated @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	System.out.println("don`t pass the validation");
            return "redirect:/registration";
        }
        userServiceImpl.save(user);
        System.out.println("pass the validation");
        return "redirect:/registration?success=true";
    }
}
