package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.model.dto.UserReviewDto;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.repository.BookStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final BookStateRepository bookStateRepository;

    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(BookStateRepository bookStateRepository, BookRepository bookRepository) {
        this.bookStateRepository = bookStateRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Boolean updateUserReview(String username, Long bookId, UserReview data) {

//        return bookStateRepository.findOneByUser_NameAndBook_Id(username, bookId)
//                .map(state -> {
//                    state.setUserReview(data);
//                    return bookStateRepository.save(state);
//                })
//                .isPresent();
        return null;
    }

//    todo refactor
    @Override
    public Boolean addReview(UserReview review, Long bookId) {

        return Optional.ofNullable(bookRepository.getOne(bookId))
                .flatMap(book -> {
                    book.getReviews().add(review);
                    return Optional.ofNullable(bookRepository.save(book));
                })
                .isPresent();
    }

    //    todo refactor
    @Override
    public List<UserReview> getReviews(Long bookId) {

        return Optional.ofNullable(bookRepository.getOne(bookId))
                .map(Book::getReviews)
                .orElse(new ArrayList<>());
    }
}
