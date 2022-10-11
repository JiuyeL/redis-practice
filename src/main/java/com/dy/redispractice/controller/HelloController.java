package com.dy.redispractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongyang
 * @date 2022/10/9 20:53
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public String test(){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String hello = valueOperations.get("hello");
        return hello;
    }
}
