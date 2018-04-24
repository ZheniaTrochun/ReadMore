package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.api.RemoteBookApi;
import com.yevhenii.kpi.readmore.converter.BookResponseToBookConverter;
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
    private final BookResponseToBookConverter bookConverter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, RemoteBookApi bookApi, BookResponseToBookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookApi = bookApi;
        this.bookConverter = bookConverter;
    }

    @Override
    public Optional<Book> findOneBookByNameAndAuthor(String name, String author) {

        Optional<Book> bookFromDb =
                bookRepository.findBookByNameContainingAndAuthorContaining(name, author);

        if (bookFromDb.isPresent()) {
            return bookFromDb;
        }

        return bookApi.getOneBookByNameAndAuthor(name, author)
                .map(bookConverter)
                .map(b -> {
                    bookRepository.save(b);
                    return b;
                });
    }

    @Override
    public List<Book> findManyBooksByNameAndAuthor(String name, String author) {

        List<Book> booksFromDb =
                bookRepository.findAllByNameContainingAndAuthorContaining(name, author);

        List<Book> booksFromApi = bookApi.getBooksByNameAndAuthor(name, author)
                .stream()
                .map(bookConverter)
                .collect(Collectors.toList());

        return merge(booksFromDb, booksFromApi);
    }

    private List<Book> merge(List<Book> fromDb, List<Book> fromApi) {

        return Stream.concat(fromDb.stream(), fromApi.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
