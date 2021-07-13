package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.book.Book;
import com.tw.bootcamp.bookshop.book.BookRepository;
import com.tw.bootcamp.bookshop.user.CreateUserCommand;
import com.tw.bootcamp.bookshop.user.User;
import com.tw.bootcamp.bookshop.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateOrder() {
        Book book = bookRepository.save(new Book(1L,"some-name",100,"some-author"));
        User user = userRepository.save(User.createFrom(new CreateUserCommand("some-email","some-password")));

        long orderId = 1L;
        Order order = order(orderId, user, book);

        OrderResponse savedOrder = orderService.create(order);
        assertNotNull(savedOrder);

        Order expectedOrder = orderRepository.findById(orderId).get();
        assertNotNull(expectedOrder);
        assertEquals(expectedOrder.getBook().getId(),1L);
        assertNotNull(expectedOrder.getUser());
    }

    private Order order(long id, User user, Book book) {
        Order order = new Order(id,
                user,
                book);
        return order;
    }
}