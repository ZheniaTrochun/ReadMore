package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.User;
import org.springframework.validation.BindingResult;

public interface AdminService {

    boolean login(String username, String password);

    User register(String username, String email, String password, BindingResult result)
            throws RegistrationException;

}
