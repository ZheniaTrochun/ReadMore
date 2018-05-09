package com.yevhenii.kpi.readmore.utils;

import com.yevhenii.kpi.readmore.security.CredsHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterUtilsImpl implements TwitterUtils {

    private final String consumerKey;
    private final String consumerSecret;


    public TwitterUtilsImpl(@Value("${spring.social.twitter.appId}") String consumerKey,
                            @Value("${spring.social.twitter.appSecret}") String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }


    @Override
    public Tweet tweet(String text) {

        return createTemplate()
                .timelineOperations()
                .updateStatus(text);
    }

    @Override
    public List<String> getFriendNames() {

        return createTemplate()
                .friendOperations()
                .getFriends()
                .stream()
                .map(TwitterProfile::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Tweet tweet(String text, Twitter twitter) {

        return twitter.timelineOperations().updateStatus(text);
    }

    @Override
    public List<String> getFriendNames(Twitter twitter) {

        return twitter
                .friendOperations()
                .getFriends()
                .stream()
                .map(TwitterProfile::getName)
                .collect(Collectors.toList());
    }


    @Override
    public TwitterTemplate createTemplate() {
        CredsHolder creds =
                (CredsHolder) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getCredentials();

        return new TwitterTemplate(consumerKey, consumerSecret, creds.getAccessToken(), creds.getSecret());
    }
}
