package com.yevhenii.kpi.readmore.security.config;

import com.yevhenii.kpi.readmore.security.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.SignInAdapter;

@Configuration
@Slf4j
public class SocialConfiguration {

    @Bean
    public SignInAdapter signInAdapter() {
        return (userId, connection, request) -> {
            log.info("userId = " + userId);
            log.info("connection = " + connection.toString());
            log.info("request = " + request.toString());

            AuthUtils.authenticate(connection);
            return null;
        };
    }
}
