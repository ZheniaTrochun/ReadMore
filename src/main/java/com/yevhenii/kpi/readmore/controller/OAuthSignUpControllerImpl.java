package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.service.OAuthSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OAuthSignUpControllerImpl implements OAuthSignUpController {

    private final OAuthSignUpService signUpService;

    @Autowired
    public OAuthSignUpControllerImpl(OAuthSignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping("/signup")
    public ModelAndView signup(WebRequest request) {

        return signUpService.signup(request) ?
                new ModelAndView("redirect:/") : new ModelAndView("redirect:/error");
    }
}
