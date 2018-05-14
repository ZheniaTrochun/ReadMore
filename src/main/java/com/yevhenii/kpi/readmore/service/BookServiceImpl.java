package com.yevhenii.kpi.readmore.service;

import com.google.common.base.Strings;
import com.yevhenii.kpi.readmore.api.RemoteBookApi;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.BookDto;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public boolean addReview(UserReview review, Long bookId) {

        return bookRepository.findOneById(bookId)
                .flatMap(book -> {
                    book.getReviews().add(review);
                    return Optional.of(bookRepository.save(book));
                })
                .isPresent();
    }

    @Override
    public List<Book> getBooksFromDb() {
        return bookRepository.findAll();
    }

    @Override
    public boolean deleteReview(Long bookId, Long id) {

        //        return bookRepository.findOneById(bookId)
//                .flatMap(b -> b.getReviews()
//                        .stream()
//                        .filter(review -> review.getId().equals(id))
//                        .findFirst().map(b.getReviews()::remove))
//                .orElse(false);

        return bookRepository.findOneById(bookId)
                .flatMap(b -> {
                    Optional<Boolean> res = b.getReviews()
                            .stream()
                            .filter(review -> review.getId().equals(id))
                            .findFirst().map(b.getReviews()::remove);
                    bookRepository.save(b);

                    return res;
                })
                .orElse(false);
    }

    @Override
    public Book createBook(BookDto dto) {

        return bookRepository.save(
                Book.builder()
                        .name(dto.getName())
                        .author(dto.getAuthor())
                        .description(dto.getDescription())
                        .year(dto.getYear())
                        .imageUrl(Strings.isNullOrEmpty(dto.getImageUrl()) ? "" : dto.getImageUrl())
                        .genre(dto.getGenre())
                        .build()
        );
    }

    @Override
    public boolean deleteBook(Long bookId) {
        try {
            bookRepository.delete(bookId);
            return true;
        } catch (Exception e) {
            return false;
        }
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
