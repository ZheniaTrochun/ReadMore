package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.utils.TwitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    @Value("${spring.social.twitter.appId}")
    String consumerKey;
    @Value("${spring.social.twitter.appSecret}")
    String consumerSecret;

    private final TwitterUtils twitterUtils;

    @Autowired
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository, TwitterUtils twitterUtils) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.twitterUtils = twitterUtils;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends")
    public ResponseEntity<List<String>> friends() {

        return ResponseEntity.ok(twitterUtils.getFriendNames());
    }

//    todo refactor
    @RequestMapping(method = RequestMethod.GET, value = "/tweet")
    public ResponseEntity<Void> tweet() {

        return new ResponseEntity<>(
                twitterUtils.tweet("Read More!").getText().equals("Read More!") ?
                        HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
