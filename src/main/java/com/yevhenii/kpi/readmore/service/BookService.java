package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findOneBookByNameAndAuthor(String name, String author);

    List<Book> findManyBooksByNameAndAuthor(String name, String author);

    Book save(Book book);

    Book saveOrGetIfPresent(Book book);

    List<UserReview> getReviews(Long bookId);

    boolean addReview(UserReview review, Long bookId);

    List<Book> getBooksFromDb();

    boolean deleteReview(Long bookId, Long id);

    Book createBook(BookDto dto);

    boolean deleteBook(Long bookId);
}