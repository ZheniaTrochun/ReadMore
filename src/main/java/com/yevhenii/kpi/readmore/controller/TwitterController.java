package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.security.CredsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    @Value("${spring.social.twitter.appId}")
    String consumerKey;
    @Value("${spring.social.twitter.appSecret}")
    String consumerSecret;

    @Autowired
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends")
    public String friends(HttpServletRequest request) {
//        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
//            return "redirect:/connect/twitter";
//        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);

        CredsHolder creds = (CredsHolder) authentication.getCredentials();

        Twitter template = new TwitterTemplate(consumerKey, consumerSecret, creds.getAccessToken(), creds.getSecret());

        return String.join(",", template.friendOperations().getFriends().stream().map(TwitterProfile::getName).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tweet")
    public String tweet() {
//        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
//            return "redirect:/connect/twitter";
//        }
//        Twitter twitter = new TwitterTemplate(SecurityContextHolder.getContext().getAuthentication().getCredentials());

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
