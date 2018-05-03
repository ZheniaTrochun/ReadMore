package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserReviewDto {

    @NotNull
    private Integer rating;
    private String description;
    @NotNull
    private Long bookId;

    public UserReviewDto() {
    }

    public UserReviewDto(Integer rating, String description, Long bookId) {
        this.rating = rating;
        this.description = description;
        this.bookId = bookId;
    }
}
