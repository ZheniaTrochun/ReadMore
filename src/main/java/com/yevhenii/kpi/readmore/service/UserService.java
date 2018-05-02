package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.User;

import java.util.Optional;

public interface UserService {

    User register(String username, String email, String password) throws RegistrationException;

    Optional<User> findUserByUsername(String username);
}