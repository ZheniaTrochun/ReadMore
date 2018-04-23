package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.Book;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BookRepository extends Repository<Book, Long> {

    Optional<Book> findBookByNameAndAuthor(String name, String author);
}
