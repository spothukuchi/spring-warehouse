package com.cegres.springwarehouse.productservice.controller;

import com.cegres.springwarehouse.productservice.model.Product;
import com.cegres.springwarehouse.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody Product product) {
        boolean saved = false;
        saved = productService.save(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Boolean> get(@RequestParam String name){
       Product product = productService.get(name);
       boolean exists = product!=null;
       return ResponseEntity.ok(exists);
   }

    /*@GetMapping
    public ResponseEntity<Boolean> isExist(@RequestParam String name) {
        boolean exist = productService.isExist(name);
        return ResponseEntity.ok(exist);
    }*/

}
