package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.service.BookStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book/state")
public class BookStateControllerImpl implements BookStateController {

    private final BookStateService bookStateService;

    @Autowired
    public BookStateControllerImpl(BookStateService bookStateService) {
        this.bookStateService = bookStateService;
    }


    @Override
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserTodo(@RequestParam Long bookId, ServletRequest request) {

        boolean success =
                bookStateService.deleteState(bookId, (String) request.getAttribute("user"));

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
