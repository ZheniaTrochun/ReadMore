package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user/progress")
public class ProgressController {

    private final UserService userService;

    @Autowired
    public ProgressController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getUserProgress(ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        return ResponseEntity.ok(
                username
                        .map(userService::getUserProgress)
                        .orElse(new ArrayList<>())
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addToUserProgress(@RequestBody Book book, ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        username
                .ifPresent(name -> userService.addProgress(book, name));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteToUserProgress(@RequestParam Long bookId, ServletRequest request) {
        Optional<String> username = Optional.ofNullable((String) request.getAttribute("user"));

        username
                .ifPresent(name -> userService.deleteProgress(bookId, name));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
