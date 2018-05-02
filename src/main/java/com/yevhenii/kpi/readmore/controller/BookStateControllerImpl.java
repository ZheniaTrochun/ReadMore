package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import com.yevhenii.kpi.readmore.service.BookStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book/state")
public class BookStateControllerImpl implements BookStateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookStateControllerImpl.class);

    private final BookStateService bookStateService;

    @Autowired
    public BookStateControllerImpl(BookStateService bookStateService) {
        this.bookStateService = bookStateService;
    }


    @Override
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserTodo(@RequestParam Long bookId, ServletRequest request) {

        LOGGER.info("Delete method called");

        boolean success =
                bookStateService.deleteState(bookId, (String) request.getAttribute("user"));

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.NOT_MODIFIED
        );
    }

    @Override
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public ResponseEntity<NotesResponse> getUserNotes(@RequestParam Long bookId, ServletRequest request) {

        String username = (String) request.getAttribute("user");

        return bookStateService.getUserNotes(bookId, username)
                .map(notes -> ResponseEntity.ok(new NotesResponse(notes)))
                .orElse(ResponseEntity.status(404).build());
    }

    @Override
    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserNotes(@RequestBody UserNotesDto notes, ServletRequest request) {

        String username = (String) request.getAttribute("user");

        Boolean success = bookStateService.updateUserNotes(notes.getNotes(), notes.getBookId(), username);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.NOT_MODIFIED
        );
    }

    @Override
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserTodo(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(
                        (String) request.getAttribute("user"),
                        State.TODO
                )
        );
    }

    @Override
    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserTodo(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.addTodoItem(book, (String) request.getAttribute("user"));

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.NOT_MODIFIED
        );
    }

    @Override
    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserProgress(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(
                        (String) request.getAttribute("user"),
                        State.IN_PROGRESS
                )
        );
    }

    @Override
    @RequestMapping(value = "/progress", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserProgress(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.changeState(book, (String) request.getAttribute("user"), State.IN_PROGRESS);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.NOT_MODIFIED
        );
    }

    @Override
    @RequestMapping(value = "/finished", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserFinished(ServletRequest request) {

        return ResponseEntity.ok(
                bookStateService.getBooksByStateAndUser(
                        (String) request.getAttribute("user"),
                        State.FINISHED
                )
        );
    }

    @Override
    @RequestMapping(value = "/finished", method = RequestMethod.POST)
    public ResponseEntity<Void> addUserFinished(@RequestBody Book book, ServletRequest request) {

        boolean success =
                bookStateService.changeState(book, (String) request.getAttribute("user"), State.FINISHED);

        return new ResponseEntity<>(
                success ? HttpStatus.OK : HttpStatus.NOT_MODIFIED
        );
    }
}
