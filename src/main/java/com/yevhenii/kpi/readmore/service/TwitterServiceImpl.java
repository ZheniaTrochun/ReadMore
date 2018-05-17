package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.security.CredsHolder;
import com.yevhenii.kpi.readmore.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TwitterServiceImpl implements TwitterService {

    private final String consumerKey;
    private final String consumerSecret;

    private final BookStateService bookStateService;
    private final BookRepository bookRepository;


    public TwitterServiceImpl(@Value("${spring.social.twitter.appId}") String consumerKey,
                              @Value("${spring.social.twitter.appSecret}") String consumerSecret,
                              BookStateService bookStateService, BookRepository bookRepository) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.bookStateService = bookStateService;
        this.bookRepository = bookRepository;
    }


    @Override
    public Tweet tweet(String text) {

        return createTemplate()
                .timelineOperations()
                .updateStatus(text);
    }

    @Override
    public Tweet tweet(String text, Twitter twitter) {

        return twitter.timelineOperations().updateStatus(text);
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

    @Override
    public Boolean tweetList(State state) {
        Twitter twitter = createTemplate();
        String username = SecurityUtils.getUsername();

        Map<String, Integer> tweetRows = bookStateService.getBooksByStateAndUser(username, state)
                .stream()
                .map(this::constructTweetRow)
                .collect(Collectors.toMap(row -> row, String::length));

        Set<String> keys = tweetRows.keySet();
        List<String> tweets = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = keys.iterator();

        int counter = 0;

        for (int i = 0; i < tweetRows.size(); i++) {
            String row = iterator.next();
            counter += tweetRows.get(row);

            if (counter >= 230) {
                tweets.add(builder.toString());
                builder = new StringBuilder();
                counter = 0;
            }

            builder.append(row);
            builder.append('\n');
        }

        tweets.add(builder.toString());

        tweets.set(0, constructHeader(state, tweets.get(0)));
        tweets.set(tweets.size() - 1, constructFooter(tweets.get(tweets.size() - 1)));

        try {
            for (int i = tweets.size() - 1; i <= 0; i++) {
                twitter.timelineOperations().updateStatus(tweets.get(i));
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private String constructHeader(State state, String tweetBody) {
        if (state.equals(State.TODO)) {
            return "I really want to read this books:\n" + tweetBody;
        }

        if (state.equals(State.IN_PROGRESS)) {
            return "I'm reading this books:\n" + tweetBody;
        }

        if (state.equals(State.FINISHED)) {
            return "I read this books:\n" + tweetBody;
        }

        return tweetBody;
    }

    private String constructFooter(String tweetBody) {
        return tweetBody + "\nRead More!";
    }

    @Override
    public Boolean tweetBook(State state, Long bookId) {

        return bookRepository.findOneById(bookId)
                .map(book -> createTemplate()
                        .timelineOperations().
                                updateStatus(constructFooter(constructHeader(state, constructTweetRow(book)))))
                .isPresent();
    }

    private String constructTweetRow(Book book) {
        return String.format("%s by %s", book.getName(), book.getAuthor());
    }
}
