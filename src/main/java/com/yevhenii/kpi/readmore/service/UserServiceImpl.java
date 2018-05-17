package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        return userRepository.findUserByName(username);
    }
}
