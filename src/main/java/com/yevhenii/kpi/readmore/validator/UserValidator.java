package com.yevhenii.kpi.readmore.validator;

import com.yevhenii.kpi.readmore.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (Objects.isNull(target)){
            errors.reject("user", "user_is_null");
            return;
        }

        if (user.getEmail().isEmpty()) {
            errors.reject("email", "email_is_empty");
        }

        if (user.getHashedPass().isEmpty()) {
            errors.reject("password", "password_is_empty");
        }

        if (user.getEmail().isEmpty()) {
            errors.reject("username", "username_is_empty");
        }
    }
}
