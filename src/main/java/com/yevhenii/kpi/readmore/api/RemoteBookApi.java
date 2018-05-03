package com.yevhenii.kpi.readmore.api;

import com.yevhenii.kpi.readmore.model.Book;

import java.util.List;
import java.util.Optional;

public interface RemoteBookApi {

    List<Book> getBooksByNameAndAuthor(String name, String author);

    Optional<Book> getBookById(String id);

    Optional<Book> getOneBookByNameAndAuthor(String name, String author);
}
