package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import java.util.List;

public interface UserReviewController {

    ResponseEntity<Void> updateForBook(UserReview data, Long bookId, ServletRequest request);

    ResponseEntity<List<UserReview>> getReviews(Long bookId, ServletRequest request);

    ResponseEntity<Void> addReviews(UserReviewDto review, ServletRequest request);
}
