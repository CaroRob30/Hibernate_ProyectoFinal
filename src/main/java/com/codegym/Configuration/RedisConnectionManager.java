package com.codegym.Configuration;

import io.lettuce.core.api.StatefulRedisConnection;

public class RedisConnectionManager {
    private final StatefulRedisConnection<String, String> redisConnection;

    public RedisConnectionManager(RedisConfigManager redisClient) {
        this.redisConnection = redisClient.getRedisClient().prepareRedisClient().connect();
    }

    public StatefulRedisConnection<String, String> getRedisConnection() {
        return redisConnection;
    }

    public void redisShutdown() {
        if (redisConnection != null) {
            redisConnection.close();
        }
    }
}
