package com.cegres.springwarehouse.productservice.repository;

import com.cegres.springwarehouse.productservice.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    Product product1, product2;

    @BeforeEach
    public void setUp(){
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
    public void shouldSave(){
        productRepository.save(product1);
        Assertions.assertThat(productRepository.findAll().stream().findAny().get()).isEqualTo(product1);
      }

    @Test
    public void shouldCheckByProductName(){
        productRepository.save(product1);
        productRepository.save(product2);
        Assertions.assertThat(productRepository.findByName("Machine1")).isEqualTo(product1);
        Assertions.assertThat(productRepository.findByName("Machine2")).isEqualTo(product2);
    }

}