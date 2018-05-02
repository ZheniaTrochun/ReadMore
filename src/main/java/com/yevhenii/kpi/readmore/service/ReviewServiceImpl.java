package com.yevhenii.kpi.readmore.service;

import com.yevhenii.kpi.readmore.model.UserReview;
import com.yevhenii.kpi.readmore.repository.BookStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final BookStateRepository bookStateRepository;

    @Autowired
    public ReviewServiceImpl(BookStateRepository bookStateRepository) {
        this.bookStateRepository = bookStateRepository;
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
}
