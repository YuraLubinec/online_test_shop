package com.yuralubinec.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.UserServiceImpl;

@Component
public class UserValidator implements Validator {

    @Autowired
    User user;

    @Autowired
    UserServiceImpl userServiceImpl;

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
