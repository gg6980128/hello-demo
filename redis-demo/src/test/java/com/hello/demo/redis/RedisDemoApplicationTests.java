package com.hello.demo.redis;

import com.hello.starter.redis.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {
        Integer test = RedisUtils.getCacheObject("test");
        System.out.println(test);
    }

}
