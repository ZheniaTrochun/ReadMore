package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;

import java.util.List;

public interface ReviewService {

    Boolean updateUserReview(String username, Long bookId, UserReview data);

    List<UserReview> getReviews(Long bookId);

    Boolean addReview(UserReview review, Long bookId);
}
