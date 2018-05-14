package com.yevhenii.kpi.readmore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "author" })
})
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String author;
    private Integer year;

    @Column(columnDefinition = "text")
    private String description;
    private String genre;
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserReview> reviews;

    public Book() {
        this.reviews = new ArrayList<>();
    }

    public Book(Long id, @NotNull String name, @NotNull String author,
                Integer year, String description, String genre, String imageUrl) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.reviews = new ArrayList<>();
    }

    public Book(Long id, @NotNull String name, @NotNull String author, Integer year,
                String description, String genre, String imageUrl, List<UserReview> reviews) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return getName().equals(book.getName()) &&
                getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getName(), getAuthor());
    }
}
