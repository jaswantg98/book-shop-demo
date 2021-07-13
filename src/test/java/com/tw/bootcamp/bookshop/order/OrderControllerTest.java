package com.tw.bootcamp.bookshop.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.bootcamp.bookshop.book.Book;
import com.tw.bootcamp.bookshop.user.CreateUserCommand;
import com.tw.bootcamp.bookshop.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAnOrder() throws Exception {
        Order order = new Order(1L,
                User.createFrom(new CreateUserCommand("some-email","some-password")),
                new Book(1L,"some-name",100,"some-author"));

        when(orderService.create(any(Order.class))).thenReturn(new OrderResponse(1L));

        mockMvc.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order))).andExpect(status().isCreated()).
                andExpect(jsonPath("$.id").value(1));

        verify(orderService, times(1)).create(any(Order.class));
    }
}
