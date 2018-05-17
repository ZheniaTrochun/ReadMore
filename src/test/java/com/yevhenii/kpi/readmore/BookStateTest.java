package com.yevhenii.kpi.readmore;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.State;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.service.BookService;
import com.yevhenii.kpi.readmore.service.BookStateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStateTest {

    @Autowired
    private BookStateService bookStateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    private Book book;

    @Before
    public void setUp() {
        try {
            userRepository.save(new User("username", "123"));

            book =
                    bookService.save(Book.builder()
                            .name("name")
                            .author("author")
                            .description("description")
                            .year(2018)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createStateTest() {
        Assert.assertEquals(bookStateService.getBooksByStateAndUser("username", State.TODO).size(), 0);
        bookStateService.addTodoItem(book, "username");

        Assert.assertEquals(bookStateService.getBooksByStateAndUser("username", State.TODO).size(), 1);
    }

    @Test
    @Ignore
    public void changeStateTest() {
        Assert.assertEquals(1, bookStateService.getBooksByStateAndUser("username", State.TODO).size());

        Boolean res = bookStateService.changeState(
                bookService.findOneBookByNameAndAuthor("name", "author").get(),
                "username",
                State.FINISHED);

        Assert.assertTrue(res);
        Assert.assertEquals(0, bookStateService.getBooksByStateAndUser("username", State.TODO).size());
        Assert.assertEquals(1, bookStateService.getBooksByStateAndUser("username", State.FINISHED).size());
    }
}
