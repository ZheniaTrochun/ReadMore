package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import com.yevhenii.kpi.readmore.service.BookStateService;
import com.yevhenii.kpi.readmore.utils.ControllerUtils;
import com.yevhenii.kpi.readmore.utils.SecurityUtils;
import com.yevhenii.kpi.readmore.utils.converter.BookToBookResponseConverter;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public ResponseEntity<Void> deleteUserTodo(@RequestParam @NotNull Long bookId) {

        String username = SecurityUtils.getUsername();

        boolean success =
                bookStateService.deleteState(bookId, username);

        LOGGER.debug(String.format("Deleting book state for user[%s], bookId[%d], result[%s]",
                username,
                bookId,
                success ? "OK" : "FAILED"));

        return ControllerUtils.okOrBadRequest(success);
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets user notes for book",
            response = NotesResponse.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public ResponseEntity<NotesResponse> getUserNotes(@RequestParam @NotNull Long bookId) {

        String username = SecurityUtils.getUsername();

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
    public ResponseEntity<Void> updateUserNotes(@RequestBody @Valid UserNotesDto notes) {

        String username = SecurityUtils.getUsername();

        Boolean success = bookStateService.updateUserNotes(notes.getNotes(), notes.getBookId(), username);

        LOGGER.debug(String.format("Editing notes for user[%s], bookId[%d], result[%s]",
                username,
                notes.getBookId(),
                success ? "OK" : "FAILED"));

        return ControllerUtils.okOrBadRequest(success);
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's todo-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserTodo() {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(SecurityUtils.getUsername(), State.TODO)
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
    public ResponseEntity<Void> addUserTodo(@RequestBody @Valid Book book) {

        String username = SecurityUtils.getUsername();

        boolean success =
                bookStateService.addTodoItem(book, username);

        LOGGER.info(String.format("Todo addition for user[%s], bookId[%d], result[%s]",
                username,
                book.getId(),
                success ? "OK" : "FAILED"));

        return ControllerUtils.okOrBadRequest(success);
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's progress-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserProgress() {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(SecurityUtils.getUsername(), State.IN_PROGRESS)
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
    public ResponseEntity<Void> addUserProgress(@RequestBody @Valid Book book) {

        String username = SecurityUtils.getUsername();

        boolean success =
                bookStateService.changeState(book, username, State.IN_PROGRESS);

        LOGGER.debug(String.format("In-Progress item addition for user[%s], bookId[%d], result[%s]",
                username,
                book.getId(),
                success ? "OK" : "FAILED"));

        return ControllerUtils.okOrBadRequest(success);
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets all books from user's finished-list",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/finished", method = RequestMethod.GET)
    public ResponseEntity<List<BookResponse>> getUserFinished() {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(SecurityUtils.getUsername(), State.FINISHED)
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
    public ResponseEntity<Void> addUserFinished(@RequestBody @Valid Book book) {

        String username = SecurityUtils.getUsername();

        boolean success =
                bookStateService.changeState(book, username, State.FINISHED);

        LOGGER.debug(String.format("Finished item addition for user[%s], bookId[%d], result[%s]",
                username,
                book.getId(),
                success ? "OK" : "FAILED"));

        return ControllerUtils.okOrBadRequest(success);
    }
}
