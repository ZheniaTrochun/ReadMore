package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user/finished")
public class FinishedController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FinishedController.class);

    private final UserService userService;

    @Autowired
    public FinishedController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserFinished(ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        return ResponseEntity.ok(
              username
                      .map(userService::getUserFinished)
                      .orElse(new ArrayList<>())
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addUserFinished(@RequestBody Book book, ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        username
                .ifPresent(name -> userService.addFinished(book, name));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserFinished(@RequestParam Long bookId, ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        LOGGER.info("Deleting from finished for user, book = " + bookId);

        username
                .ifPresent(name -> userService.deleteFinished(bookId, name));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
