package com.yy.guava.demo;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateLimiterTest {

    @Test
    public void rateLimiter(){
        RateLimiter rateLimiter = RateLimiter.create(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 8000; i++) {
            rateLimiter.acquire();
        }
        System.out.println("cost:" + (System.currentTimeMillis() - start) + "ms");


    }
}
