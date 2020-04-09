package com.cegres.springwarehouse.productservice.model;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProductTest {

    Product product1, product2;

    @BeforeEach
    public void setUp() {
        product1 = Product.builder()
                .id(1L)
                .name("Machine1")
                .description("Washing Machine")
                .build();

        product2 = Product.builder()
                .id(2L)
                .name("Machine2")
                .description("Dish Washer")
                .build();
    }

    @Test
    public void buildAccessTest() {
        Assertions.assertThat(product1.getName()).isEqualTo("Machine1");
        Assertions.assertThat(product2.getName()).isEqualTo("Machine2");
    }

}