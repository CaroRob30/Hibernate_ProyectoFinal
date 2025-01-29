package com.codegym.Service.Redis;

import com.codegym.Configuration.RedisConfig;
import com.codegym.redis.CityCountry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.util.List;

public class RedisService {

    private final RedisConfig redisClient;
    private final ObjectMapper mapper;

    public RedisService(RedisConfig redisClient, ObjectMapper mapper) {
        this.redisClient = redisClient;
        this.mapper = mapper;
    }

    public void pushDataToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.prepareRedisClient().connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.prepareRedisClient().connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
