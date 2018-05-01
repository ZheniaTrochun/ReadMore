package com.yevhenii.kpi.readmore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class UserReview {

    private Integer rating;
    private String description;

    @Column(columnDefinition = "text")
    private String notes;

    public UserReview() {
    }

    public UserReview(Integer rating, String description, String notes) {
        this.rating = rating;
        this.description = description;
        this.notes = notes;
    }
}
