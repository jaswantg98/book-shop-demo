package com.tw.bootcamp.bookshop.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldGetBooks() throws Exception {

        when(bookService.get()).thenReturn(Arrays.asList(new Book(1L,"some-book",100,"some-author")));

        mockMvc.perform(MockMvcRequestBuilders.get("/books")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1)).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("some-book"));

        verify(bookService,times(1)).get();
    }

}
