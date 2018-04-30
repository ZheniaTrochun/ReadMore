package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.exception.EmailIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.exception.UsernameIsAlreadyTakenException;
import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookState;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.encoder = encoder;
    }

//    todo remove exceptions
    @Override
    public User register(String username, String email, String password)
            throws UsernameIsAlreadyTakenException, EmailIsAlreadyTakenException {

        if (userRepository.findUserByName(username).isPresent()) {
            throw new UsernameIsAlreadyTakenException();
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new EmailIsAlreadyTakenException();
        }

        return userRepository.save(
                new User(username, email, encoder.encode(password), "ROLE_USER"));
    }

//    @Transactional
//    public List<Book> getBooksByStateAndUser(String username, State state) {
//
//        return userRepository.findUserByName(username)
//                .map(user -> user.getBooks()
//                        .stream()
//                        .filter(b -> b.getState().equals(state))
//                        .map(BookState::getBook)
//                        .collect(Collectors.toList()))
//                .orElse(new ArrayList<>());
//    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        return userRepository.findUserByName(username);
    }

//
//    @Override
//    @Transactional
//    @Async
//    public void addTodo(Book book, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    if (!user.getTodo().contains(book)) {
//                        if (book.getId() != null) {
//                            Optional.ofNullable(bookRepository.getOne(book.getId()))
//                                    .ifPresent(b -> user.getTodo().add(b));
//                        } else {
//                            user.getTodo().add(book);
//                        }
//                        userRepository.save(user);
//                    }
//                });
//    }

//    @Override
//    @Transactional
//    @Async
//    public void addProgress(Book book, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    if (!user.getInProgress().contains(book)) {
//                        if (book.getId() != null) {
//                            Optional.ofNullable(bookRepository.getOne(book.getId()))
//                                    .ifPresent(b -> {
//                                        user.getInProgress().add(b);
//                                        user.getTodo().remove(b);
//                                    });
//                        } else {
//                            user.getInProgress().add(book);
//                        }
//                        userRepository.save(user);
//                    }
//                });
//    }

//    @Override
//    @Transactional
//    @Async
//    public void addFinished(Book book, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    if (!user.getFinished().contains(book)) {
//                        if (book.getId() != null) {
//                            Optional.ofNullable(bookRepository.getOne(book.getId()))
//                                    .ifPresent(b -> {
//                                        user.getFinished().add(b);
//                                        user.getInProgress().remove(b);
//                                        user.getTodo().remove(b);
//                                    });
//                        } else {
//                            user.getInProgress().add(book);
//                        }
//                        userRepository.save(user);
//                    }
//                });
//    }

//    @Override
//    @Transactional
//    public void deleteTodo(Long bookId, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    user.getTodo().remove(bookRepository.getOne(bookId));
//                    userRepository.save(user);
//                });
//    }

//    @Override
//    @Transactional
//    public void deleteProgress(Long bookId, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    user.getInProgress().remove(bookRepository.getOne(bookId));
//                    userRepository.save(user);
//                });
//    }

//    @Override
//    @Transactional
//    public void deleteFinished(Long bookId, String username) {
//        userRepository.findUserByName(username)
//                .ifPresent(user -> {
//                    user.getFinished().remove(bookRepository.getOne(bookId));
//                    userRepository.save(user);
//                });
//    }
}
