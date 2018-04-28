package com.yevhenii.kpi.readmore.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

    private String id;
    private String name;
    private String author;
    private String genre;
    private Integer year;
    private String description;
    private String imageLink;

    public BookResponse() {
    }

    public BookResponse(String id, String name, String author, String genre, Integer year, String description, String imageLink) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.imageLink = imageLink;
    }
}
