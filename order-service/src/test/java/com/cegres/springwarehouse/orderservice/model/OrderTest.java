package com.cegres.springwarehouse.orderservice.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

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
    public void orderShouldBuild(){
        Assertions.assertThat(order.userId).isEqualTo("PSK123");
    }



}