package com.yevhenii.kpi.readmore.controller;


import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.BookDto;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import com.yevhenii.kpi.readmore.model.response.BookResponse;
import com.yevhenii.kpi.readmore.service.BookService;
import com.yevhenii.kpi.readmore.utils.ControllerUtils;
import com.yevhenii.kpi.readmore.utils.SecurityUtils;
import com.yevhenii.kpi.readmore.utils.converter.BookToBookResponseConverter;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

//    todo replace
    private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerImpl.class);

    private final BookService bookService;

    private final BookToBookResponseConverter converter;

    @Autowired
    public BookControllerImpl(BookService bookService, BookToBookResponseConverter converter) {
        this.bookService = bookService;
        this.converter = converter;
    }


    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets books from DB and from remote api by name and author",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(method = GET, produces = "application/json")
    public Callable<ResponseEntity<List<BookResponse>>> findBooksByNameAndAuthor(@RequestParam String name,
                                                                                 @RequestParam String author) {

        LOGGER.debug(String.format("find book called with name = %s and author = %s", name, author));

        return () -> {
            List<BookResponse> books = bookService.findManyBooksByNameAndAuthor(name, author)
                    .stream()
                    .map(converter)
                    .collect(Collectors.toList());

            LOGGER.debug(String.format("found books, length = %d", books.size()));

            return ResponseEntity.ok(books);
        };
    }


    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Gets one book by name and author",
            response = BookResponse.class,
            produces = "application/json"
    )
    @RequestMapping(path = "/one", method = GET, produces = "application/json")
    public Callable<ResponseEntity<BookResponse>> findOneBookByNameAndAuthor(@RequestParam String name,
                                                                             @RequestParam String author) {

        LOGGER.debug(String.format("find book called with name = %s and author = %s", name, author));

        return () -> {
            Optional<BookResponse> book = bookService.findOneBookByNameAndAuthor(name, author)
                    .map(converter);

            LOGGER.debug(String.format("found book option = %s", book.toString()));

            return book
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        };
    }

    @Override
    @RequestMapping("/book/all")
    public ResponseEntity<List<BookResponse>> getBooksFromDb() {

        return ResponseEntity.ok(
                bookService.getBooksFromDb()
                        .stream()
                        .map(converter)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ApiOperation(
            httpMethod = "GET",
            value = "Endpoint for receiving reviews for book",
            response = List.class,
            produces = "application/json"
    )
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public ResponseEntity<List<UserReview>> getReviews(@RequestParam @NotNull Long bookId) {

        return ResponseEntity.ok(bookService.getReviews(bookId));
    }

    @Override
    @ApiOperation(
            httpMethod = "POST",
            value = "Endpoint for new review creation"
    )
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ResponseEntity<Void> addReviews(@RequestBody @NotNull UserReviewDto reviewDto) {

        String username = SecurityUtils.getUsername();

        UserReview review = new UserReview(
                reviewDto.getRating(),
                reviewDto.getDescription(),
                username);

        Boolean success = bookService.addReview(review, reviewDto.getBookId());

        LOGGER.debug("Review addition done, success = " + success);

        return ControllerUtils.okOrBadRequest(success);
    }

    @Override
    @RequestMapping(value = "/review", method = DELETE)
    public ResponseEntity<Void> deleteReview(@RequestParam Long bookId, @RequestParam String author, @RequestParam Date date) {

        return ControllerUtils.okOrBadRequest(bookService.deleteReview(bookId, author, date));
    }

    @Override
    @RequestMapping(method = POST, produces = "application/json")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookDto dto) {

        return ResponseEntity.ok(
                converter.apply(bookService.createBook(dto))
        );
    }
}
