package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.api.RemoteBookApi;
import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.utils.converter.BookResponseToBookConverter;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final RemoteBookApi bookApi;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RemoteBookApi bookApi) {
        this.bookRepository = bookRepository;
        this.bookApi = bookApi;
    }

    @Override
    public Optional<Book> findOneBookByNameAndAuthor(String name, String author) {

//        first we try to find from db
        Optional<Book> fromDb = bookRepository.findBookByNameAndAuthor(name, author);

        if(fromDb.isPresent()) {
            LOGGER.debug("Book found in Database, book = " + fromDb.get().toString());

            return fromDb;
        }

        LOGGER.debug("Book not found in Database, fetching from api...");
//        if there isn't suitable book in db, we ask api
        return bookApi
                .getOneBookByNameAndAuthor(name, author)
                .map(bookRepository::save);
    }

    @Override
    public List<Book> findManyBooksByNameAndAuthor(String name, String author) {

//        fetching all suitable from Database
        List<Book> booksFromDb =
                bookRepository.findAllByNameContainingAndAuthorContaining(name, author);

//        fetching top-10 results from api
        List<Book> booksFromApi = bookApi.getBooksByNameAndAuthor(name, author);

//        merging all results
        return merge(booksFromDb, booksFromApi);
    }

    @Override
    public Book save(Book book) {

        return bookRepository.save(book);
    }

//    main purpose of this method - avoid book data updating
    @Override
    public Book saveOrGetIfPresent(Book book) {
        if (Objects.isNull(book.getId())) {
            try {
                LOGGER.debug("Book id is null, saving to Database");

                return save(book);
            } catch (Exception e) {
                LOGGER.warn("Tried to save duplicated book!");

                return bookRepository.findBookByNameAndAuthor(book.getName(), book.getAuthor()).get();
            }
        }

        return bookRepository.getOne(book.getId());
    }

//    select distinct books from two lists
    private List<Book> merge(List<Book> fromDb, List<Book> fromApi) {

        return Stream.concat(fromDb.stream(), fromApi.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Save new review to list
     *
     * @param review - review
     * @param bookId - book id
     * @return true if all good and false if book not found
     */
    @Override
    public Boolean addReview(UserReview review, Long bookId) {

        return bookRepository.findOneById(bookId)
                .flatMap(book -> {
                    book.getReviews().add(review);
                    return Optional.of(bookRepository.save(book));
                })
                .isPresent();
    }

    /**
     * Gets all reviews for book as list
     *
     * @param bookId - book id
     * @return list of reviews or empty list if something gone wrong
     */
    @Override
    public List<UserReview> getReviews(Long bookId) {

        return bookRepository.findOneById(bookId)
                .map(Book::getReviews)
                .orElse(new ArrayList<>());
    }
}
