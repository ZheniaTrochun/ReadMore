package com.yevhenii.kpi.readmore.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;

@Slf4j
public class AuthUtils {
    public static void authenticate(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        Connection<Twitter> twitterConnection = (Connection<Twitter>) connection;
        String username = profile.getUsername();
        ConnectionData data = twitterConnection.createData();
        CredsHolder creds = new CredsHolder(data.getSecret(), data.getAccessToken());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, creds, null);
        SecurityContextHolder.getContext().setAuthentication(token);
        log.info("User {} {} connected", profile.getFirstName(), profile.getLastName());
    }
}
