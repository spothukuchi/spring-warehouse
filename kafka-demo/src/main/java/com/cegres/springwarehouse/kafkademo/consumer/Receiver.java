package com.cegres.springwarehouse.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    //i=1
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public CountDownLatch getCountDownLatch(){
        return countDownLatch;
    }

    @KafkaListener(topics = "payment.t")
    public void receive(String payload){
        countDownLatch.countDown();
        //1=0
        System.out.println("Payload -->" + payload);
    }
}
