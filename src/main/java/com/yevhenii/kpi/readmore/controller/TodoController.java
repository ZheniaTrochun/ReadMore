package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/todo")
public class TodoController {

    private final UserService userService;

    @Autowired
    public TodoController(UserService userService) {
        this.userService = userService;
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
}
