package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

@Data
public class UserReviewDto {

    private Integer rating;
    private String description;
    private Long bookId;

    public UserReviewDto() {
    }

    public UserReviewDto(Integer rating, String description, Long bookId) {
        this.rating = rating;
        this.description = description;
        this.bookId = bookId;
    }
}
