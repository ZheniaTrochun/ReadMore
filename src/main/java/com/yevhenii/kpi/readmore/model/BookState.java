package com.yevhenii.kpi.readmore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
