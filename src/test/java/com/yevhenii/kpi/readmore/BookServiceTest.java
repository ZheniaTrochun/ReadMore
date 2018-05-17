package com.yevhenii.kpi.readmore;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.service.BookService;
import com.yevhenii.kpi.readmore.service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    private BookRepository repository;

    private BookService bookService;

    private final Book bookWithId = new Book(1L, "", "", 0, "", "", "");
    private final Book bookWithOutId = Book.builder().name("name").author("author").build();

    @Before
    public void setup() {
        repository = Mockito.mock(BookRepository.class);

        Mockito.when(repository.findBookByNameAndAuthor("name", "author"))
                .thenReturn(Optional.of(bookWithOutId));

        Mockito.when(repository.save(bookWithOutId))
                .thenThrow(new RuntimeException());

        Mockito.when(repository.save(Matchers.any(Book.class)))
                .thenReturn(bookWithOutId);

        Mockito.when(repository.getOne(1L))
                .thenReturn(bookWithId);


        this.bookService = new BookServiceImpl(repository, null);
    }

    @Test
    public void saveOrGetIfPresentTestWithId() {
        Book book = bookService.saveOrGetIfPresent(bookWithId);

        Assert.assertEquals(bookWithId, book);
        Mockito.verify(repository, times(0)).findBookByNameAndAuthor(Matchers.any(), Matchers.any());
    }

    @Test
    public void saveOrGetIfPresentTestWithoutId() {
        Book book = bookService.saveOrGetIfPresent(bookWithOutId);

        Assert.assertEquals(bookWithOutId, book);
        Mockito.verify(repository, times(0)).getOne(Matchers.any());
        Mockito.verify(repository, times(1)).save(bookWithOutId);
        Mockito.verify(repository).save(Matchers.any(Book.class));
    }

}
