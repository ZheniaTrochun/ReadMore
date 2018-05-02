package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import com.yevhenii.kpi.readmore.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book/review")
public class UserReviewControllerImpl implements UserReviewController {

    private final ReviewService reviewService;

    @Autowired
    public UserReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Endpoint for receiving reviews for book",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserReview>> getReviews(@RequestParam Long bookId, ServletRequest request) {

        return ResponseEntity.ok(reviewService.getReviews(bookId));
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Endpoint for new review addition"
    )
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addReviews(@RequestBody UserReviewDto review, ServletRequest request) {

        UserReview review1 =
                new UserReview(review.getRating(), review.getDescription(), (String) request.getAttribute("user"));

        reviewService.addReview(review1, review.getBookId());

        return new ResponseEntity<>(HttpStatus.OK );
    }
}
