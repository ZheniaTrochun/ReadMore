package com.yevhenii.kpi.readmore.model.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class BookDto {
    @NotNull
    private String name;
    @NotNull
    private String author;
    private Integer year;
    private String description;
    private String genre;
    private String imageUrl;

    public BookDto() {
    }

    public BookDto(String name, String author, Integer year, String description, String genre, String imageUrl) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
    }
}
