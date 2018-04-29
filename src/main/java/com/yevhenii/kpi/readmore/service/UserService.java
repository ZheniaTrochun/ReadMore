package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.User;

import java.util.List;

public interface UserService {

    User register(String username, String email, String password)
            throws UsernameIsAlreadyTakenException, EmailIsAlreadyTakenException;

    List<Book> getUserTodos(String username);

    List<Book> getUserProgress(String username);

    List<Book> getUserFinished(String username);

    void addTodo(Book book, String username);

    void addProgress(Book book, String username);

    void addFinished(Book book, String username);

    void deleteTodo(Long bookId, String username);

    void deleteProgress(Long bookId, String username);

    void deleteFinished(Long bookId, String username);
}