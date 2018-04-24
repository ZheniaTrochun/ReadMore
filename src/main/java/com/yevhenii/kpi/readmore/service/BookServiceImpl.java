package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.api.BookResponse;
import com.yevhenii.kpi.readmore.api.RemoteBookApi;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookBuilder;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        Optional<Book> bookFromDb = bookRepository.findBookByNameContainingAndAuthorContaining(name, author);

        if (bookFromDb.isPresent()) {
            return bookFromDb;
        }

        Optional<BookResponse> apiResponse = bookApi.getOneBookByNameAndAuthor(name, author);

        if (!apiResponse.isPresent()) {
            return Optional.empty();
        }

        BookResponse resp = apiResponse.get();

        Book book = new BookBuilder()
                .setName(resp.getName())
                .setAuthor(resp.getAuthor())
                .setGenre(resp.getGenre())
                .setYear(resp.getYear())
                .setImageUrl(resp.getImageLink())
                .createBook();

        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public List<Book> findManyBooksByNameAndAuthor(String name, String author) {
        return null;
    }

//    private List<Book> merge(List<Book> fromDb, List<Book> fromApi) {
//        List<Book> res = new <>(fromDb);
//        res.addAll(fromApi);
//
//        return res
//    }
}
