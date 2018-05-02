package com.yevhenii.kpi.readmore.controller;


import com.yevhenii.kpi.readmore.utils.converter.BookToBookResponseConverter;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;

    private final BookToBookResponseConverter converter;

    @Autowired
    public BookControllerImpl(BookService bookService, BookToBookResponseConverter converter) {
        this.bookService = bookService;
        this.converter = converter;
    }


    @Override
    @RequestMapping(method = GET, produces = "application/json")
    public Callable<ResponseEntity<List<BookResponse>>> findBooksByNameAndAuthor(@RequestParam String name,
                                                                                 @RequestParam String author) {

        LOGGER.debug(String.format("find book called with name = %s and author = %s", name, author));

        return () -> {
            List<BookResponse> books = bookService.findManyBooksByNameAndAuthor(name, author)
                    .stream()
                    .map(converter)
                    .collect(Collectors.toList());

            LOGGER.debug(String.format("found books, length = %d", books.size()));

            return ResponseEntity.ok(books);
        };
    }


    @Override
    @RequestMapping(path = "/one", method = GET, produces = "application/json")
    public Callable<ResponseEntity<BookResponse>> findOneBookByNameAndAuthor(@RequestParam String name,
                                                                             @RequestParam String author) {

        LOGGER.info(String.format("find book called with name = %s and author = %s", name, author));

        return () -> {
            Optional<BookResponse> book = bookService.findOneBookByNameAndAuthor(name, author)
                    .map(converter);

            LOGGER.info(String.format("found book option = %s", book.toString()));

            return book
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        };
    }

    @RequestMapping(value = "/test", method = GET)
    public String test() {
        return "Hello";
    }
}
