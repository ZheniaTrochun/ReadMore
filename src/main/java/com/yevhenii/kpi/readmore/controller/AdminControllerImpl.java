package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.model.dto.UserLoginDto;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.service.AdminService;
import com.yevhenii.kpi.readmore.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Void> login(@RequestBody @Valid UserLoginDto dto) {

        return ControllerUtils.okOrBadRequest(
                adminService.login(dto.getUsername(), dto.getPassword())
        );
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterDto dto, BindingResult result)
            throws RegistrationException {

        return ControllerUtils.okOrBadRequest(
                !Objects.isNull(adminService.register(dto.getUsername(), dto.getEmail(), dto.getPassword(), result))
        );
    }
}
