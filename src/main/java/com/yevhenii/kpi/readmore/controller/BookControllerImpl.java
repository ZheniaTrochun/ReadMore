package com.yevhenii.kpi.readmore.controller;


import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.Callable;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    @RequestMapping(method = GET, produces = "application/json")
    public Callable<ResponseEntity<Book>> findBookByNameAndAuthor(@RequestParam String name,
                                                                  @RequestParam String author) {

        LOGGER.info(String.format("find book called with name = %s and author = %s", name, author));

        return () -> {
            Optional<Book> book = bookService.findOneBookByNameAndAuthor(name, author);

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
