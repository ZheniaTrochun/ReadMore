package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.exception.UserValidationFailedException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookState;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
    private final UserValidator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, UserValidator validator) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.validator = validator;
    }

    /**
     * Registers new user or throws exception if email or username is already taken
     *
     * @param username user name
     * @param email user email
     * @param password raw password
     * @return created user
     * @throws RegistrationException if email or username is already taken or validation failed
     */
    @Override
    public User register(String username, String email, String password, BindingResult result)
            throws RegistrationException {

        User newUser = new User(username, email, encoder.encode(password), "ROLE_USER");

        validator.validate(newUser, result);

        if (result.hasErrors()) {
            LOGGER.warn("Validation failed");

            throw new UserValidationFailedException();
        }

        if (userRepository.findUserByName(username).isPresent()) {
            LOGGER.warn("Username is already taken, email = " + email);

            throw new UsernameIsAlreadyTakenException();
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            LOGGER.warn("Email is already taken, email = " + email);

            throw new EmailIsAlreadyTakenException();
        }

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        return userRepository.findUserByName(username);
    }
}
