package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.State;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.List;

public interface TwitterService {

    Tweet tweet(String text);

    List<String> getFriendNames();

    Tweet tweet(String text, Twitter twitter);

    List<String> getFriendNames(Twitter twitter);

    TwitterTemplate createTemplate();

    Boolean tweetList(State state);

    Boolean tweetBook(State state, Long bookId);
}
