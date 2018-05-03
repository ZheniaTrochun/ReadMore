package com.yevhenii.kpi.readmore.utils.converter;


import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class BookToBookResponseConverter implements Function<Book, BookResponse> {

    @Override
    public BookResponse apply(Book book) {

        return BookResponse
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .year(book.getYear())
                .imageUrl(book.getImageUrl())
                .description(book.getDescription())
                .averageRating(constructAverageRating(book))
                .reviewsLength(constructReviewsLength(book))
                .build();
    }

    private Integer constructReviewsLength(Book book) {

        return Objects.isNull(book.getReviews()) ? 0 : book.getReviews().size();
    }

    private Double constructAverageRating(Book book) {

        return Objects.isNull(book.getReviews()) ?
                0 :
                    book.getReviews()
                            .stream()
                            .mapToInt(UserReview::getRating)
                            .average()
                            .orElse(0);
    }
}
