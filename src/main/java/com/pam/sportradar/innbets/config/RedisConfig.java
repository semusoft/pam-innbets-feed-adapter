package com.pam.sportradar.innbets.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create("redis://" + host + ":" + port + "/");
    }

    @Bean
    public RedisAsyncCommands<String, String> redisAsyncCommands() {
        return redisClient().connect().async();
    }
}
