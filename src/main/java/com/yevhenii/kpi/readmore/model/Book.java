package com.yevhenii.kpi.readmore.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String author;
    private Integer year;

    private String genre;
    private String imageUrl;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
