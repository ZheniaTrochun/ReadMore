package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.UserReview;

public interface ReviewService {

    Boolean updateUserReview(String username, Long bookId, UserReview data);
}
