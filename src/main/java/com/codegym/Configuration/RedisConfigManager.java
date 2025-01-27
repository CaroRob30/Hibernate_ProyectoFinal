package com.codegym.Configuration;

public class RedisConfigManager {
    private final RedisConfig redisClient;

    public RedisConfigManager() {
        this.redisClient = new RedisConfig();
    }

    public RedisConfig getRedisClient() {
        return redisClient;
    }
}
