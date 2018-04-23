package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;

import java.util.Optional;

public interface BookService {

    Optional<Book> findBookByNameAndAuthor(String name, String author);
}
