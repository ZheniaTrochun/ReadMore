package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.State;
import org.springframework.http.ResponseEntity;

public interface TwitterController {

    ResponseEntity<Void> tweetList(State state);

    ResponseEntity<Void> tweetBook(State state, Long bookId);
}
