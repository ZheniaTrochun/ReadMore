package com.yevhenii.kpi.readmore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@Builder
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

    public Book() {
    }

    public Book(Long id, @NotNull String name, @NotNull String author, Integer year, String description, String genre, String imageUrl) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(getName(), book.getName()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getYear(), book.getYear());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getName(), getAuthor(), getYear());
    }
}
