package com.mayur.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testSendEmail(){
        redisTemplate.opsForValue().set("email", "mtest@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        Object salary = redisTemplate.opsForValue().get("salary");
    }
}
