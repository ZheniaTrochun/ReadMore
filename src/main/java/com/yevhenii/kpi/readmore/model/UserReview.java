package com.yevhenii.kpi.readmore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class UserReview {

    private Integer rating;
    private String description;

    private String author;

    public UserReview() {
    }

    public UserReview(Integer rating, String description, String author) {
        this.rating = rating;
        this.description = description;
        this.author = author;
    }
}
