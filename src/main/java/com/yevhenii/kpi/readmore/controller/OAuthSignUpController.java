package com.yevhenii.kpi.readmore.controller;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

public interface OAuthSignUpController {

    ModelAndView signup(WebRequest request);
}
