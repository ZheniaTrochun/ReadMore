package com.yevhenii.kpi.readmore.security;

import com.yevhenii.kpi.readmore.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class AuthUtils {
    public static void authenticateWithTwitter(Connection<Twitter> connection) {
        UserProfile profile = connection.fetchUserProfile();
        String username = profile.getUsername();
        ConnectionData data = connection.createData();
        CredsHolder creds = new CredsHolder(data.getSecret(), data.getAccessToken());

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, creds, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);

        log.info("User {} {} connected", profile.getFirstName(), profile.getLastName());
    }

    public static User authenticateAdmin(User user) {
        String username = user.getName();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        SecurityContextHolder.getContext().setAuthentication(token);

        log.info("Admin {} connected", username);

        return user;
    }
}
