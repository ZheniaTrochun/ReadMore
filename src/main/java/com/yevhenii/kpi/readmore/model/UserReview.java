package com.yevhenii.kpi.readmore.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserReview {

    @Id
    @GeneratedValue
    private Long id;

    private Integer rating;
    private String description;

    @Column(columnDefinition = "blob")
    private String notes;

//    todo link
//    private User user;
//    private Book book;
}
