package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.BookDto;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.Callable;

public interface BookController {

    Callable<ResponseEntity<List<BookResponse>>> findBooksByNameAndAuthor(String name, String author);

    Callable<ResponseEntity<BookResponse>> findOneBookByNameAndAuthor(String name, String author);

    ResponseEntity<List<BookResponse>> getBooksFromDb();

    ResponseEntity<List<UserReview>> getReviews(Long bookId);

    ResponseEntity<Void> addReviews(UserReviewDto review);

    ResponseEntity<Void> deleteReview(String author, Date date);

    ResponseEntity<BookResponse> createBook(BookDto dto);
}
