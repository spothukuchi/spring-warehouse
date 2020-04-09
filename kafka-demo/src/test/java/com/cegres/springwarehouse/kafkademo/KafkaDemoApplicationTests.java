package com.cegres.springwarehouse.kafkademo;

import com.cegres.springwarehouse.kafkademo.consumer.Receiver;
import com.cegres.springwarehouse.kafkademo.producer.Sender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class KafkaDemoApplicationTests {

    @Autowired
    private Receiver receiver;

    @Autowired private Sender sender;

    @Test
    public void testMessageReceived() throws InterruptedException {
        sender.send("Hello This is a first message");
        CountDownLatch countDownLatch = receiver.getCountDownLatch();
        countDownLatch.await(1000, TimeUnit.MILLISECONDS);
        Assertions.assertThat(countDownLatch.getCount()).isEqualTo(0);

    }

}
