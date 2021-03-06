package com.yuralubinec.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.UserService;

/**
 * Implementation of {@link Validator} interface for additional checking {@link User} instance. Checks the
 * unique of user's login parameter.
 *
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       
        User user = (User) target;
        if (!userServiceImpl.isLoginUnique(user.getLogin(), user.getId())) {
            errors.rejectValue("login", "NotUnique.login");
        }
    }
}
