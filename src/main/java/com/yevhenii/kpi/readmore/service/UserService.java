package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsername(String username);
}