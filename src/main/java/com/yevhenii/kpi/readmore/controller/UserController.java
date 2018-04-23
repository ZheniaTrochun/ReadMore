package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.model.dto.UserLoginDto;
import com.yevhenii.kpi.readmore.model.dto.UserRegisterDto;

public interface UserController {

    Boolean register(UserRegisterDto registerDto);

    Boolean login(UserLoginDto loginDto);
}
