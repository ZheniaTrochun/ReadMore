package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<Void> register(UserRegisterDto registerDto) throws RegistrationException;
}
