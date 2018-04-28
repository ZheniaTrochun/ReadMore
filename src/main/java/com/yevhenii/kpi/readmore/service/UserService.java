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

    void addTodo(Book book, String username);
}
