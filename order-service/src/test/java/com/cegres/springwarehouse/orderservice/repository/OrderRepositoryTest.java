package com.cegres.springwarehouse.orderservice.repository;

import com.cegres.springwarehouse.orderservice.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    Order order;

    @BeforeEach
    public void setUp(){
        order = Order.builder()
                .id(1)
                .userId("PSK123")
                .quantity(1)
                .address("1 James ST, NY")
                .name("Machine1")
                .description("Washing Machine")
                .build();
    }

    @Test
    public void shouldSave(){
        orderRepository.save(order);
        Optional<Order> byId = orderRepository.findById(1);
        Assertions.assertThat(byId).isNotEmpty();
        Assertions.assertThat(byId.get().getUserId()).isEqualTo(order.getUserId());
    }

}