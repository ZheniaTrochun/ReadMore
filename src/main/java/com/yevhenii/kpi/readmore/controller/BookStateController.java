package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import java.util.List;

public interface BookStateController {

    ResponseEntity<Void> deleteUserTodo(Long bookId, ServletRequest request);

    ResponseEntity<NotesResponse> getUserNotes(Long bookId, ServletRequest request);

    ResponseEntity<Void> updateUserNotes(UserNotesDto notes, ServletRequest request);

    ResponseEntity<List<Book>> getUserTodo(ServletRequest request);

    ResponseEntity<Void> addUserTodo(Book book, ServletRequest request);

    ResponseEntity<List<Book>> getUserProgress(ServletRequest request);

    ResponseEntity<Void> addUserProgress(Book book, ServletRequest request);

    ResponseEntity<List<Book>> getUserFinished(ServletRequest request);

    ResponseEntity<Void> addUserFinished(Book book, ServletRequest request);

}
