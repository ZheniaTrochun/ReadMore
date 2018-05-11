package com.yevhenii.kpi.readmore.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Embeddable
public class UserReview {

    private Integer rating;
    private String description;

    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    private String author;

    public UserReview() {
    }

    public UserReview(Integer rating, String description, String author) {
        this.rating = rating;
        this.description = description;
        this.author = author;
    }
}
