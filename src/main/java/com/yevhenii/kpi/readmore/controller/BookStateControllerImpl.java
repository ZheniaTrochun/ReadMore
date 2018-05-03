package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.utils.converter.BookToBookResponseConverter;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import com.yevhenii.kpi.readmore.service.BookStateService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book/state")
public class BookStateControllerImpl implements BookStateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookStateControllerImpl.class);

    private final BookStateService bookStateService;

    private final BookToBookResponseConverter converter;

    @Autowired
    public BookStateControllerImpl(BookStateService bookStateService, BookToBookResponseConverter converter) {
        this.bookStateService = bookStateService;
        this.converter = converter;
    }


    @Override
    @ApiOperation(
            httpMethod = "DELETE",
            value = "Removes book state for user (removes mapping between user and book)"
    )
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserTodo(@RequestParam Long bookId, ServletRequest request) {

        String username = (String) request.getAttribute("user");

        boolean success =
                bookStateService.deleteState(bookId, username);

        LOGGER.debug(String.format("Deleting book state for user[%s], bookId[%d], result[%s]",
                username,
                bookId,
                success ? "OK" : "FAILED"));

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets user notes for book",
            response = NotesResponse.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public ResponseEntity<NotesResponse> getUserNotes(@RequestParam Long bookId, ServletRequest request) {

        String username = (String) request.getAttribute("user");

        return bookStateService.getUserNotes(bookId, username)
                .map(notes -> ResponseEntity.ok(new NotesResponse(notes)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @ApiOperation(
            httpMethod = "PUT",
            value = "Updates user's notes for book"
    )
    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserNotes(@RequestBody UserNotesDto notes, ServletRequest request) {

        String username = (String) request.getAttribute("user");

        Boolean success = bookStateService.updateUserNotes(notes.getNotes(), notes.getBookId(), username);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's todo-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserTodo(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser((String) request.getAttribute("user"), State.TODO)
                        .stream()
                        .map(converter)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Adds new book to todo-list"
    )
    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserTodo(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.addTodoItem(book, (String) request.getAttribute("user"));

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's progress-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserProgress(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser((String) request.getAttribute("user"), State.IN_PROGRESS)
                        .stream()
                        .map(converter)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Adds new book to progress-list"
    )
    @RequestMapping(value = "/progress", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserProgress(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.changeState(book, (String) request.getAttribute("user"), State.IN_PROGRESS);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's finished-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/finished", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserFinished(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser((String) request.getAttribute("user"), State.FINISHED)
                        .stream()
                        .map(converter)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Adds new book to finished-list"
    )
    @RequestMapping(value = "/finished", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserFinished(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.changeState(book, (String) request.getAttribute("user"), State.FINISHED);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
