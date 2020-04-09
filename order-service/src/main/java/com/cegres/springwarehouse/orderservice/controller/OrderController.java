package com.cegres.springwarehouse.orderservice.controller;

import com.cegres.springwarehouse.orderservice.model.Order;
import com.cegres.springwarehouse.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody Order order){
        if(orderService.createOrder(order)){
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        }else{
            throw new OrderService.OrderNotCreatedException();
        }
    }
}
