package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.service.TwitterService;
import com.yevhenii.kpi.readmore.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/twitter")
public class TwitterControllerImpl implements TwitterController {

    private final Twitter twitter;

    private final ConnectionRepository connectionRepository;

    @Value("${spring.social.twitter.appId}")
    String consumerKey;
    @Value("${spring.social.twitter.appSecret}")
    String consumerSecret;

    private final TwitterService twitterService;

    @Autowired
    public TwitterControllerImpl(Twitter twitter,
                                 ConnectionRepository connectionRepository,
                                 TwitterService twitterService) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.twitterService = twitterService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends")
    public ResponseEntity<List<String>> friends() {

        return ResponseEntity.ok(twitterService.getFriendNames());
    }

//    todo refactor
    @RequestMapping(method = RequestMethod.GET, value = "/tweet")
    public ResponseEntity<Void> tweet() {

        return new ResponseEntity<>(
                twitterService.tweet("Read More!").getText().equals("Read More!") ?
                        HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/tweet/list")
    public ResponseEntity<Void> tweetList(@RequestParam State state) {

        return ControllerUtils.okOrBadRequest(twitterService.tweetList(state));
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/tweet/book")
    public ResponseEntity<Void> tweetBook(@RequestParam State state, @RequestParam Long bookId) {

        return ControllerUtils.okOrBadRequest(twitterService.tweetBook(state, bookId));
    }
}
