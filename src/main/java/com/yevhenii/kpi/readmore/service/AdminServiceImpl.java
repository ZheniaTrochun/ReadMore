package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.exception.UserValidationFailedException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.security.AuthUtils;
import com.yevhenii.kpi.readmore.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserValidator validator;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder encoder, UserValidator validator) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.validator = validator;
    }

    @Override
    public boolean login(String username, String password) {

        return userRepository.findUserByName(username)
                .filter(u -> encoder.matches(password, u.getHashedPass()))
                .map(AuthUtils::authenticateAdmin)
                .isPresent();
    }

    @Override
    public User register(String username, String email, String password, BindingResult result)
            throws RegistrationException {

        User newUser = new User(username, email, encoder.encode(password), "ROLE_ADMIN");
        validator.validate(newUser, result);

        if (result.hasErrors()) {
            log.warn("Validation failed");
            throw new UserValidationFailedException();
        }

        return register(username, email, password);
    }

    @Override
    public User register(String username, String email, String password) throws RegistrationException {
        User newUser = new User(username, email, encoder.encode(password), "ROLE_ADMIN");

        if (userRepository.findUserByName(username).isPresent()) {
            log.warn("Username is already taken, username = " + username);
            throw new UsernameIsAlreadyTakenException();
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            log.warn("Email is already taken, email = " + email);
            throw new EmailIsAlreadyTakenException();
        }

        return userRepository.save(newUser);
    }
}
