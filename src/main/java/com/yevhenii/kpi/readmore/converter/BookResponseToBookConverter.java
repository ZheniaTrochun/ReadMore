package com.yevhenii.kpi.readmore.converter;

import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookResponseToBookConverter implements Function<BookResponse, Book> {

    @Override
    public Book apply(BookResponse resp) {

        return Book
                .builder()
                .name(resp.getName())
                .author(resp.getAuthor())
                .genre(resp.getGenre())
                .year(resp.getYear())
                .imageUrl(resp.getImageLink())
                .description(resp.getDescription())
                .build();
    }
}
