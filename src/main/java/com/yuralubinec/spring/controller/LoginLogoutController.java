package com.yuralubinec.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {
    
    private static final Logger LOGGER = Logger.getLogger(LoginLogoutController.class); 
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model){
        
//    	if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
//    	    System.out.println("fail");
//            return "login";
//        }
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            System.out.println(auth);
            new SecurityContextLogoutHandler().logout(request, response, auth);
            System.out.println(auth);
        }
        return "redirect:/login?logout=true";
    }
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(Model model) {
        model.addAttribute("message", "You don't have permission to access this page");
        return "error";
    }
    
}
