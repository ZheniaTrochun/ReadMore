package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.security.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@Service
public class OAuthSignUpService {

    private final ProviderSignInUtils signInUtils;
    private final UserRepository userRepository;

    @Autowired
    public OAuthSignUpService(ConnectionFactoryLocator connectionFactoryLocator,
                              UsersConnectionRepository connectionRepository,
                              UserRepository userRepository) {
        this.userRepository = userRepository;
        this.signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    //    todo add dao
    //    todo think about duplicated usernames
    public boolean signup(WebRequest request) {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);

        if (Objects.isNull(connection)){
            return false;
        }

        String name = connection.getDisplayName().substring(1);

        userRepository.findUserByName(name).orElseGet(() -> {
            User user = User.builder()
                    .name(name)
                    .strategy("twitter")
                    .build();

            return userRepository.save(user);
        });

        AuthUtils.authenticate(connection);
        signInUtils.doPostSignUp(connection.getDisplayName(), request);

        return true;
    }

}