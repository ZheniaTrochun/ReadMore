package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookState;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public User register(String username, String email, String password)
            throws RegistrationException {

        if (userRepository.findUserByName(username).isPresent()) {
            throw new UsernameIsAlreadyTakenException();
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new EmailIsAlreadyTakenException();
        }

        return userRepository.save(
                new User(username, email, encoder.encode(password), "ROLE_USER"));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        return userRepository.findUserByName(username);
    }
}
