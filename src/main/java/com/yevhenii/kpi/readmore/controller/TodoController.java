package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.converter.BookResponseToBookConverter;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/user/todo")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final UserService userService;
    private final BookResponseToBookConverter converter;

    @Autowired
    public TodoController(UserService userService, BookResponseToBookConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserTodos(HttpServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        return ResponseEntity.ok(
                username
                        .map(userService::getUserTodos)
                        .orElse(new ArrayList<>())
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addUserTodos(@RequestBody BookResponse book, HttpServletRequest request) {

        LOGGER.debug("Adding todo for user, book = " + book.toString());
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        username
                .ifPresent(name -> userService.addTodo(converter.apply(book), name));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserTodo(@RequestParam Long bookId, HttpServletRequest request) {

        LOGGER.debug("Deleting todo for user, book = " + bookId);
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        username
                .ifPresent(name -> userService.deleteTodo(bookId, name));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
