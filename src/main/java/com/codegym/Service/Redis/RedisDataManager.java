package com.codegym.Service.Redis;

import com.codegym.Redis.CityCountry;

import java.util.List;

/*
Esta clase actúa como intermediario para gestionar la interacción con Redis a través de 'RedisService'.
Su constructor recibe una instancia de 'RedisService'. Proporciona dos métodos: 'pushToRedis(List<CityCountry> data)',
que envía datos a Redis, y 'testRedis(List<Integer> ids)', que ejecuta pruebas de rendimiento accediendo a
Redis con los ids proporcionados.
*/

public class RedisDataManager {
    private final RedisService redisService;

    public RedisDataManager(RedisService redisService) {
        this.redisService = redisService;
    }

    public void pushToRedis(List<CityCountry> data) {
        redisService.pushDataToRedis(data);
    }

    public void testRedis(List<Integer> ids) {
        redisService.testRedisData(ids);
    }
}
