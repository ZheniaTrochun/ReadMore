package com.yevhenii.kpi.readmore.model;

public class BookBuilder {
    private String name;
    private String author;
    private Integer year;
    private String genre;
    private String imageUrl;

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public BookBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public BookBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Book createBook() {
        return new Book(name, author, year, genre, imageUrl);
    }
}