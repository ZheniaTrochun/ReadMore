package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.concurrent.Callable;

public interface BookController {

    Callable<ResponseEntity<List<BookResponse>>> findBooksByNameAndAuthor(String name, String author);

    Callable<ResponseEntity<BookResponse>> findOneBookByNameAndAuthor(String name, String author);

    ResponseEntity<List<UserReview>> getReviews(Long bookId, ServletRequest request);

    ResponseEntity<Void> addReviews(UserReviewDto review, ServletRequest request);
}
