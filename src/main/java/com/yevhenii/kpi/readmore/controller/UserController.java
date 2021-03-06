package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.response.UsernameResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface UserController {

//    ResponseEntity<Void> register(UserRegisterDto registerDto, BindingResult result) throws RegistrationException;

    ResponseEntity<Void> isAuthenticated();

    ResponseEntity<UsernameResponse> getUsername();

    ResponseEntity<Void> logout(HttpSession session);
}
