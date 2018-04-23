package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

public interface BookController {

    Callable<ResponseEntity<Book>> findBookByNameAndAuthor(String name, String author);
}
