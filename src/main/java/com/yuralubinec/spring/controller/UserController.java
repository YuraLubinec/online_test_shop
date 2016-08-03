package com.yuralubinec.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.yuralubinec.spring.editor.ItemEditor;
import com.yuralubinec.spring.validator.UserValidator;

@Controller
public class UserController {
    
    private static final Logger LOGGER = Logger.getLogger(UserController.class); 
    
    @Autowired
    ItemEditor itemEditor;
    
    @Autowired
    UserValidator userValidator;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "userItems", itemEditor);
        binder.addValidators(userValidator);
    }

}
