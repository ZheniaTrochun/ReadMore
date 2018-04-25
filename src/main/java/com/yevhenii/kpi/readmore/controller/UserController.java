package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<Void> register(UserRegisterDto registerDto) throws UsernameIsAlreadyTakenException, EmailIsAlreadyTakenException;
}
