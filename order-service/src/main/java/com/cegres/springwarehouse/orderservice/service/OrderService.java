package com.cegres.springwarehouse.orderservice.service;

import com.cegres.springwarehouse.orderservice.model.Order;
import com.cegres.springwarehouse.orderservice.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "createOrder0")
    public boolean createOrder(Order order){
        //Fire a call to product service and check if product exists
        //"http://localhost:8081/product?name=" + order.getName()
        ResponseEntity<Boolean> forEntity = restTemplate.getForEntity("http://product-service/product?name=" + order.getName(), Boolean.class);
        if(forEntity.getBody()){
            Order saved = orderRepository.save(order);
            if(saved!=null){
                return true;
            }else{
                throw new OrderNotCreatedException();
            }
        }else{
            throw new ProductNotFoundException();
        }
    }

    public boolean createOrder0(Order order){
        return true;
    }

    public static class OrderNotCreatedException extends RuntimeException {
    }

    private class ProductNotFoundException extends RuntimeException {
    }


}
