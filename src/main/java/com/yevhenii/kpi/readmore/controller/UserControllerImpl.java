package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import com.yevhenii.kpi.readmore.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Endpoint for users registration"
    )
    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<Void> register(@RequestBody UserRegisterDto registerDto, BindingResult result)
            throws RegistrationException {

        User user = userService.register(registerDto.getUsername(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                result);

        LOGGER.debug("User registered " + user.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
