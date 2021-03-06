package com.yevhenii.kpi.readmore.security.config;

import com.yevhenii.kpi.readmore.security.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.twitter.api.Twitter;

@Configuration
@Slf4j
public class SocialConfiguration {

    @Bean
    public SignInAdapter signInAdapter() {
        return (userId, connection, request) -> {
            log.info("userId = " + userId);
            log.info("connection = " + connection.toString());
            log.info("request = " + request.toString());

            AuthUtils.authenticateWithTwitter((Connection<Twitter>) connection);
            return null;
        };
    }
}
