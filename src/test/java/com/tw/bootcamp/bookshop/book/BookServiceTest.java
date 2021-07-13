package com.tw.bootcamp.bookshop.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    BookServiceTest() {
    }

    @Test
    public void shouldGetBooks() {
        Book book = new Book(1L,"some-name",100,"some-author");
        bookRepository.save(book);

        List<Book> books = bookService.get();

        assertEquals(1, books.size());
        assertEquals("some-name",books.get(0).getName());
        assertEquals(100,books.get(0).getPrice());
        assertEquals("some-author",books.get(0).getAuthorName());
    }
}