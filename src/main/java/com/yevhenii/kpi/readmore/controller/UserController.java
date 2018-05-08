package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

public interface UserController {

    ResponseEntity<Void> register(UserRegisterDto registerDto, BindingResult result) throws RegistrationException;
//    ResponseEntity<Void> askTwitter(WebRequest request);
}
