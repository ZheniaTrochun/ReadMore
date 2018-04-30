package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
