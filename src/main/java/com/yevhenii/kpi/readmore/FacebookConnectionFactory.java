package com.yevhenii.kpi.readmore;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookAdapter;
import org.springframework.social.facebook.connect.FacebookServiceProvider;

public class FacebookConnectionFactory extends OAuth2ConnectionFactory<Facebook> {
    public FacebookConnectionFactory(String clientId, String clientSecret) {
        super("facebook", new FacebookServiceProvider(clientId, clientSecret, ""), new FacebookAdapter());
    }
}
