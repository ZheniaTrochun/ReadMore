package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;

import java.util.List;
import java.util.Optional;

public interface BookStateService {

    List<Book> getBooksByStateAndUser(String username, State state);

    Boolean addTodoItem(Book book, String username);

    Boolean changeState(Book book, String username, State target);

    Boolean deleteState(Long bookId, String username);

    Optional<String> getUserNotes(Long bookId, String username);

    Boolean updateUserNotes(String notes, Long bookId, String username);
}
