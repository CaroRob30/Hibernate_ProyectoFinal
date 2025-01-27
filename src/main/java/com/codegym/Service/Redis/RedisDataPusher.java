package com.codegym.Service.Redis;

import com.codegym.redis.CityCountry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.util.List;

public class RedisDataPusher {
    private final StatefulRedisConnection<String, String> redisConnection;
    private final ObjectMapper mapper;

    public RedisDataPusher(StatefulRedisConnection<String, String> redisConnection, ObjectMapper mapper) {
        this.redisConnection = redisConnection;
        this.mapper = mapper;
    }

    public void pushToRedis(List<CityCountry> data) {
        RedisStringCommands<String, String> syncCommands = redisConnection.sync();
        for (CityCountry cityCountry : data) {
            try {
                syncCommands.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
