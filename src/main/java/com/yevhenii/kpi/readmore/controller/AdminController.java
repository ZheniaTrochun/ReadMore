package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.dto.UserLoginDto;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface AdminController {

    ResponseEntity<Void> login(UserLoginDto dto);

    ResponseEntity<Void> register(UserRegisterDto dto, BindingResult result) throws RegistrationException;
}
