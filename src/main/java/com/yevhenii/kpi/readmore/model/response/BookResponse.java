package com.yevhenii.kpi.readmore.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

    private Long id;
    private String name;
    private String author;
    private String genre;
    private Integer year;
    private String description;
    private String imageUrl;

    private Double averageRating;
    private Integer reviewsLength;

    public BookResponse() {
    }

    public BookResponse(Long id, String name, String author, String genre, Integer year,
                        String description, String imageUrl, Double averageRating, Integer reviewsLength) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
        this.reviewsLength = reviewsLength;
    }
}
