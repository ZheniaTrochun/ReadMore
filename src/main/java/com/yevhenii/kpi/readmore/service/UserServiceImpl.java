package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

//    todo remove exceptions
    @Override
    public User register(String username, String email, String password)
            throws UsernameIsAlreadyTakenException, EmailIsAlreadyTakenException {

        if (userRepository.findUserByName(username).isPresent()) {
            throw new UsernameIsAlreadyTakenException();
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new EmailIsAlreadyTakenException();
        }

        return userRepository.save(
                new User(username, email, encoder.encode(password), "ROLE_USER"));
    }
}
