package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.State;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

public interface TwitterService {

    Tweet tweet(String text);

    Tweet tweet(String text, Twitter twitter);

    TwitterTemplate createTemplate();

    Boolean tweetList(State state);

    Boolean tweetBook(State state, Long bookId);
}
