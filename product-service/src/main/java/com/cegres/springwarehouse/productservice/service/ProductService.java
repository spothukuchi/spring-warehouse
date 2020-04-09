package com.cegres.springwarehouse.productservice.service;

import com.cegres.springwarehouse.productservice.model.Product;
import com.cegres.springwarehouse.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    Jedis jedis;

    public boolean save(Product product){
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = null;
        try {
            valueAsString = objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jedis.set(product.getName(), valueAsString);
        return productRepository.save(product) != null;
    }

    public Product get(String key)  {
        String json = jedis.get(key);
        ObjectMapper objectMapper = new ObjectMapper();
        if(StringUtils.isNotEmpty(json)){   //present in redis
            try {
                return objectMapper.readValue(json, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        } else{
            Optional<Product> byName = productRepository.findByName(key);
            String valueAsString;
            if(!byName.isPresent()) {     //not present in DB
                throw new ProductNotFoundException();
            }else {
                Product storedProduct = byName.get();
                try {
                    valueAsString = objectMapper.writeValueAsString(storedProduct);
                    jedis.set(key, valueAsString);
                    return storedProduct;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            }
    }

    public boolean isExist(String key){
        String json = jedis.get(key);
        if(StringUtils.isNotEmpty(json)){ //return from catch itself
            return true;
        } else{                           //search DB
            if(productRepository.findByName(key).isPresent()){
                return true;
            }else{
                throw new ProductNotFoundException();
            }
        }
    }

    private class ProductNotFoundException extends RuntimeException{

    }
}
