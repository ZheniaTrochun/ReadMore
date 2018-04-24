package com.yevhenii.kpi.readmore.converter;

import com.yevhenii.kpi.readmore.api.BookResponse;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookResponseToBookConverter implements Function<BookResponse, Book> {

    @Override
    public Book apply(BookResponse resp) {

        return new BookBuilder()
                .setName(resp.getName())
                .setAuthor(resp.getAuthor())
                .setGenre(resp.getGenre())
                .setYear(resp.getYear())
                .setImageUrl(resp.getImageLink())
                .createBook();
    }
}
