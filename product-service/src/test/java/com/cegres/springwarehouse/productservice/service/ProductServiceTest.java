package com.cegres.springwarehouse.productservice.service;

import com.cegres.springwarehouse.productservice.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    Jedis jedis;

    Product product;

    @BeforeEach
    public void setUp(){
        product = Product.builder()
                .id(1L)
                .name("Machine1")
                .description("Washing Machine")
                .build();
    }

    @Test
    public void shouldConnectToRedis(){
        String pong = jedis.ping();
        Assertions.assertThat(pong).isEqualTo("PONG");
    }

    @Test
    public void shouldSaveProduct()  {
        productService.save(product);
        String value = jedis.get(product.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        Product cachedProduct = null;
        try {
            cachedProduct = objectMapper.readValue(value, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(product).isEqualTo(cachedProduct);
    }

    @Test
    public void shouldGetProduct(){
        productService.save(product);
        Product cachedProduct = productService.get(product.getName());
        Assertions.assertThat(cachedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    public void checkExists(){
        Assertions.assertThat(productService.isExist(product.getName())).isTrue();
    }

}