package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/book/review")
public class UserReviewControllerImpl implements UserReviewController {

    private final ReviewService reviewService;

    @Autowired
    public UserReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateForBook(UserReview data, Long bookId,
                                              ServletRequest request) {

        String username = (String) request.getAttribute("user");

        Boolean success = reviewService.updateUserReview(username, bookId, data);

        return null;
    }
}
