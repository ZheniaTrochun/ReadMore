package com.yevhenii.kpi.readmore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    @Autowired
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends")
    public String friends() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        return String.join(",", twitter.friendOperations().getFriends().stream().map(TwitterProfile::getName).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tweet")
    public String tweet() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        twitter.timelineOperations().updateStatus("Read more!");
        return "check it!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create-auth")
    public String signin() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
        UserProfile profile = connectionRepository.findPrimaryConnection(Twitter.class).fetchUserProfile();
		String username = profile.getUsername();
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(username, null, null);
		SecurityContextHolder.getContext().setAuthentication(token);
		return "test";
    }
}
