package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(String username, String email, String password)
            throws UsernameIsAlreadyTakenException, EmailIsAlreadyTakenException;

//    List<Book> getBooksByStateAndUser(String username, State state);

    Optional<User> findUserByUsername(String username);
}