package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import java.util.List;

public interface BookStateController {

    ResponseEntity<Void> deleteUserTodo(Long bookId, ServletRequest request);

    ResponseEntity<NotesResponse> getUserNotes(Long bookId, ServletRequest request);

    ResponseEntity<Void> updateUserNotes(UserNotesDto notes, ServletRequest request);

    ResponseEntity<List<BookResponse>> getUserTodo(ServletRequest request);

    ResponseEntity<Void> addUserTodo(Book book, ServletRequest request);

    ResponseEntity<List<BookResponse>> getUserProgress(ServletRequest request);

    ResponseEntity<Void> addUserProgress(Book book, ServletRequest request);

    ResponseEntity<List<BookResponse>> getUserFinished(ServletRequest request);

    ResponseEntity<Void> addUserFinished(Book book, ServletRequest request);

}
