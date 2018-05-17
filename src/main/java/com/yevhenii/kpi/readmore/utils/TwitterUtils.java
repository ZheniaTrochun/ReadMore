package com.yevhenii.kpi.readmore.utils;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.List;

public interface TwitterUtils {

    Tweet tweet(String text);

    List<String> getFriendNames();

    Tweet tweet(String text, Twitter twitter);

    List<String> getFriendNames(Twitter twitter);

    TwitterTemplate createTemplate();
}
