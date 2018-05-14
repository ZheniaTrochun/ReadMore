package com.yevhenii.kpi.readmore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserReview {

    @Id
    @GeneratedValue
    private Long id;

    private Integer rating;
    private String description;

    private String author;

    public UserReview() {
    }

    public UserReview(Long id, Integer rating, String description, String author) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.author = author;
    }

    public UserReview(Integer rating, String description, String author) {
        this.rating = rating;
        this.description = description;
        this.author = author;
    }
}
