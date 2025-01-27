package com.codegym.Service.Redis;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

public class RedisService {
    private final StatefulRedisConnection<String, String> redisConnection;

    public RedisService(StatefulRedisConnection<String, String> redisConnection) {
        this.redisConnection = redisConnection;
    }

    public String getValue(String key) {
        RedisStringCommands<String, String> syncCommands = redisConnection.sync();
        return syncCommands.get(key);
    }
}
