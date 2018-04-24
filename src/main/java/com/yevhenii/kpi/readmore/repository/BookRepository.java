package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByNameAndAuthor(String name, String author);

    Optional<Book> findBookByNameContainingAndAuthorContaining(String name, String author);

    List<Book> findAllByNameContainingAndAuthorContaining(String name, String author);
}
