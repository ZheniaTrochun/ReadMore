package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.service.OAuthSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OAuthSignUpController {

    private final OAuthSignUpService signUpService;

    @Autowired
    public OAuthSignUpController(OAuthSignUpService signUpService) {
        this.signUpService = signUpService;
    }

//    todo add dao
    @RequestMapping("/signup")
    public ModelAndView signup(WebRequest request) {

        if (signUpService.signup(request)) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/error");
        }
    }
}
