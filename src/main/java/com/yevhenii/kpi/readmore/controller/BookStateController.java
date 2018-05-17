package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.dto.UserNotesDto;
import com.yevhenii.kpi.readmore.model.response.BookCreationResponse;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.model.response.NotesResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookStateController {

    ResponseEntity<Void> deleteUserTodo(Long bookId);

    ResponseEntity<NotesResponse> getUserNotes(Long bookId);

    ResponseEntity<Void> updateUserNotes(UserNotesDto notes);

    ResponseEntity<List<BookResponse>> getUserTodo();

    ResponseEntity<BookCreationResponse> addUserTodo(Book book);

    ResponseEntity<List<BookResponse>> getUserProgress();

    ResponseEntity<Void> addUserProgress(Book book);

    ResponseEntity<List<BookResponse>> getUserFinished();

    ResponseEntity<Void> addUserFinished(Book book);

}
