package com.codegym.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

public class RedisConfig {
    public RedisClient prepareRedisClient() {
        return RedisClient
                .create(RedisURI.create("localhost", 6379));
    }
}