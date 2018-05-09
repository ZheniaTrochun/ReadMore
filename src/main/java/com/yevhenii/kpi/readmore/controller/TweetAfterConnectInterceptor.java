package com.yevhenii.kpi.readmore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

@Slf4j
public class TweetAfterConnectInterceptor implements ConnectInterceptor<Twitter> {

    private final ConnectionRepository connectionRepository;

    public TweetAfterConnectInterceptor(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }


    public void preConnect(ConnectionFactory<Twitter> provider, MultiValueMap<String, String> parameters, WebRequest request) {
        // nothing to do
    }

    public void postConnect(Connection<Twitter> connection, WebRequest request) {
        log.info("test");

		UserProfile profile = connection.fetchUserProfile();
		String username = profile.getUsername();

//		connection.getApi().friendOperations().getFriends().stream().map(TwitterProfile::getName).forEach(log::info);
//		log.info(connection.getApi().userOperations().getScreenName());

//		UsernamePasswordAuthenticationToken token =
//				new UsernamePasswordAuthenticationToken(username, null, null);
//		SecurityContextHolder.getContext().setAuthentication(token);

//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//
//        log.info(principal.toString());
//
//        UsernamePasswordAuthenticationToken token =
//				new UsernamePasswordAuthenticationToken(username, null, null);
//
//
//		token.setDetails(authentication.getDetails());
//
//		SecurityContextHolder.getContext().setAuthentication(token);
//
//		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        Object principal1 = authentication1.getPrincipal();
//        log.info(principal1.toString());
//
//        try {
//            connectionRepository.addConnection(connection);
//        } catch (Exception e) {
//        }

//        connection.getApi().friendOperations().getFriends().stream().map(TwitterProfile::getName).forEach(log::info);
//        log.info(connection.getApi().userOperations().getScreenName());
    }
}