package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;

public interface UserReviewController {

    ResponseEntity<Void> updateForBook(UserReview data, Long bookId, ServletRequest request);
}
