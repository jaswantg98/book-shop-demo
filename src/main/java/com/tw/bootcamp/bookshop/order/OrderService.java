package com.tw.bootcamp.bookshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse create(Order order) {
        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder.getId());
    }
}
