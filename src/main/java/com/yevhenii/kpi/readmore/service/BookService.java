package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findOneBookByNameAndAuthor(String name, String author);

    List<Book> findManyBooksByNameAndAuthor(String name, String author);
}
