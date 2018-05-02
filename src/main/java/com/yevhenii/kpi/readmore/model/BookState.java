package com.yevhenii.kpi.readmore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
public class BookState {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public BookState() {
    }

    public BookState(User user, Book book, State state) {
        this.user = user;
        this.book = book;
        this.state = state;
    }

    public BookState(Long id, User user, Book book, State state) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.state = state;
    }
}
