package com.yuralubinec.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {
    
    private static final Logger LOGGER = Logger.getLogger(LoginLogoutController.class); 
    
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(Model model) {
        model.addAttribute("message", "You don't have permission to access this page");
        return "error";
    }
}
