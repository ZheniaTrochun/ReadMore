package com.yevhenii.kpi.readmore.service;

import org.springframework.web.context.request.WebRequest;

public interface OAuthSignUpService {

    boolean signup(WebRequest request);
}
