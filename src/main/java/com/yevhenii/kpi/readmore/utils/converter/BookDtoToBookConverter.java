package com.yevhenii.kpi.readmore.utils.converter;

import com.google.common.base.Strings;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.dto.BookDto;
import com.yevhenii.kpi.readmore.utils.properties.AppPropertyHolder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookDtoToBookConverter implements Function<BookDto, Book> {

    private final AppPropertyHolder.Googlebooks properties;

    public BookDtoToBookConverter(AppPropertyHolder propertyHolder) {
        this.properties = propertyHolder.getGooglebooks();
    }

    @Override
    public Book apply(BookDto dto) {
        return Book.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .description(dto.getDescription())
                .year(dto.getYear())
                .imageUrl(createImageUrl(dto.getImageUrl()))
                .genre(dto.getGenre())
                .build();
    }

    private String createImageUrl(String url) {
        return Strings.isNullOrEmpty(url) ?
                properties.getDefaultImage() : url;
    }
}
