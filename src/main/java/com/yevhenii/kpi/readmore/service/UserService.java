package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.User;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface UserService {

    User register(String username, String email, String password, BindingResult result)
            throws RegistrationException;

    Optional<User> findUserByUsername(String username);
}