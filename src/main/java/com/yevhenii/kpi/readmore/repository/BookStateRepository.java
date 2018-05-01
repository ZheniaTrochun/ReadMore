package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.BookState;
import com.yevhenii.kpi.readmore.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookStateRepository extends JpaRepository<BookState, Long> {

    List<BookState> findAllByUser_NameAndState(String name, State state);

    Optional<BookState> findOneByUser_NameAndBook(@NotNull String name, Book book);

    Optional<BookState> findOneByUser_NameAndBook_Id(@NotNull String name, Long bookId);

    void deleteAllByUser_NameAndBook_Id(@NotNull String name, Long bookId);

    Optional<Object> findOneByUser_NameAndBook_NameAndBook_Author(@NotNull String name,
                                                                  @NotNull String bookName,
                                                                  @NotNull String author);
}
