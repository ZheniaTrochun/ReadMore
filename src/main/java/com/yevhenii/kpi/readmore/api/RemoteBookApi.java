package com.yevhenii.kpi.readmore.api;

import com.yevhenii.kpi.readmore.model.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface RemoteBookApi {

    List<BookResponse> getBooksByNameAndAuthor(String name, String author);

    Optional<BookResponse> getBookById(String id);

    Optional<BookResponse> getOneBookByNameAndAuthor(String name, String author);
}
