package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.repository.UserReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final UserReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(UserReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}
