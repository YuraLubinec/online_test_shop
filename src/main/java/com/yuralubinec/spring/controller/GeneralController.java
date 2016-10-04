package com.yuralubinec.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdvice class defines objects binding in all Controller's RequestMapping methods.
 * 
 */

@ControllerAdvice
public class GeneralController {

    private static final Logger LOGGER = Logger.getLogger(GeneralController.class);
    private static final String ERROR_MASSAGE = "message";
    
    /**
     * Handles RuntimeException exception.
     * 
     * @param e instance of RuntimeException
     * @param model {@link Model} object
     * @return name of error view
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String error500(RuntimeException e, Model model) {
        
        model.addAttribute(ERROR_MASSAGE, e.getMessage());
        LOGGER.error("Internal runtime exception", e);
        return "error";
    }

}
