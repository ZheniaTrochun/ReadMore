package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookState;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.BookStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookStateServiceImpl implements BookStateService {

    private final BookStateRepository bookStateRepository;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookStateServiceImpl(BookStateRepository bookStateRepository,
                                BookService bookService, UserService userService) {
        this.bookStateRepository = bookStateRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public List<Book> getBooksByStateAndUser(String username, State state) {

        return bookStateRepository.findAllByUser_NameAndState(username, state)
                .stream()
                .filter(b -> b.getState().equals(state))
                .map(BookState::getBook)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean addTodoItem(Book book, String username) {

        return userService.findUserByUsername(username)
                .flatMap(user -> createBookState(bookService.saveOrGetIfPresent(book), user)
                        .map(bookStateRepository::save))
                .isPresent();
    }

    @Override
    public Boolean changeState(Book book, String username, State target) {

        return bookStateRepository.findOneByUser_NameAndBook_Id(username, book.getId())
                .flatMap(state -> {
                    if (state.getState().equals(target)){
                        return Optional.empty();
                    }

                    state.setState(target);

                    return Optional.of(bookStateRepository.save(state));
                })
                .isPresent();
    }

    @Override
    @Transactional
    public Boolean deleteState(Long bookId, String username) {
        try {
            bookStateRepository.deleteAllByUser_NameAndBook_Id(username, bookId);

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    private Optional<BookState> createBookState(Book book, User user) {
        if (bookStateRepository.findOneByUser_NameAndBook_Id(user.getName(), book.getId()).isPresent()) {
            return Optional.empty();
        }

        return Optional.of(
                BookState.builder()
                        .user(user)
                        .book(book)
                        .state(State.TODO)
                        .build()
        );
    }
}