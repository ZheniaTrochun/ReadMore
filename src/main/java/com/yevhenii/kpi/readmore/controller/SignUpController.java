package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.security.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignUpController {

    private final ProviderSignInUtils signInUtils;

    @Autowired
    public SignUpController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository) {
        this.signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

//    todo add dao
    @RequestMapping("/signup")
    public String signup(WebRequest request) {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);

        if (connection != null) {
            AuthUtils.authenticate(connection);
            signInUtils.doPostSignUp(connection.getDisplayName(), request);
        }

        return "redirect:/";
    }
}
