package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByNameAndAuthor(String name, String author);

    Optional<Book> findBookByNameContainingAndAuthorContaining(String name, String author);

    List<Book> findAllByNameContainingAndAuthorContaining(String name, String author);
}
