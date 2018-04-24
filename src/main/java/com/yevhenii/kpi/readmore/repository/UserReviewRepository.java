package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
