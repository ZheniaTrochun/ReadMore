//package com.yevhenii.kpi.readmore.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.UserProfile;
//
//@Slf4j
//public class AuthUtils {
//    public static void authenticate(Connection<?> connection) {
//        UserProfile profile = connection.fetchUserProfile();
//        String username = profile.getUsername();
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(username, null, null);
//        SecurityContextHolder.getContext().setAuthentication(token);
//        log.info("User {} {} connected", profile.getFirstName(), profile.getLastName());
//    }
//}
