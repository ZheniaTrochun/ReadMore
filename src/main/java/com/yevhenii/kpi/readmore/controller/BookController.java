package com.yevhenii.kpi.readmore.controller;

import com.yevhenii.kpi.readmore.model.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.Callable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public interface BookController {

    @RequestMapping(method = GET, produces = "application/json")
    Callable<ResponseEntity<List<BookResponse>>> findBooksByNameAndAuthor(@RequestParam String name,
                                                                  @RequestParam String author);

    Callable<ResponseEntity<BookResponse>> findOneBookByNameAndAuthor(String name, String author);
}
