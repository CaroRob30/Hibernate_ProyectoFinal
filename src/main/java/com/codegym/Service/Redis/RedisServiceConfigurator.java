package com.codegym.Service.Redis;

import com.codegym.Configuration.RedisConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
Esta clase configura u proporciona una instancia de 'RedisDataManager'. Para ello, crea un objeto 'RedisConfig' para
gestionar la conexión con Redis, un 'ObjectMapper' para la conversión de datos JSON y una instancia de 'RedisService'
que maneja las operaciones con Redis. Finalmente, envuelve 'RedisService' dentro de 'RedisDataManager' y lo retorna
para su uso en la aplicación.
*/

public class RedisServiceConfigurator {
    public RedisDataManager configureRedisService() {
        RedisConfig redisClient = new RedisConfig();
        ObjectMapper mapper = new ObjectMapper();
        RedisService redisService = new RedisService(redisClient, mapper);

        return new RedisDataManager(redisService);
    }
}
