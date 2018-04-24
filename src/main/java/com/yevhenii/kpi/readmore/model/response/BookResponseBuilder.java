package com.yevhenii.kpi.readmore.model.response;

public class BookResponseBuilder {
    private String id;
    private String name;
    private String author;
    private String genre;
    private Integer year;
    private String imageLink;

    public BookResponseBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BookResponseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookResponseBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookResponseBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookResponseBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public BookResponseBuilder setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public BookResponse createBookResponse() {
        return new BookResponse(id, name, author, genre, year, imageLink);
    }
}