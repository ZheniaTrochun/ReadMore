package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.UserReview;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findOneBookByNameAndAuthor(String name, String author);

    List<Book> findManyBooksByNameAndAuthor(String name, String author);

    Book save(Book book);

    Book saveOrGetIfPresent(Book book);

    List<UserReview> getReviews(Long bookId);

    Boolean addReview(UserReview review, Long bookId);
}
