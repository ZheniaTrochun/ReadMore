package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import java.util.List;

public interface UserReviewController {

    ResponseEntity<List<UserReview>> getReviews(Long bookId, ServletRequest request);

    ResponseEntity<Void> addReviews(UserReviewDto review, ServletRequest request);
}
