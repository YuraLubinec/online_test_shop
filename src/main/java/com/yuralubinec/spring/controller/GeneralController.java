package com.yuralubinec.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralController {

    private static final String ERROR_MASSAGE = "message";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String error500(RuntimeException e, Model model) {
        model.addAttribute(ERROR_MASSAGE, e.getMessage());
        return "error";
    }

}
