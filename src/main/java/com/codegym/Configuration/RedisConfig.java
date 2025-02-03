package com.codegym.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
/*
Esta clase es la encargada de configurar y preparar una instancia de RedisClient, que es una conexión cliente
para interactuar con un servidor Redis.
El método 'prepareRedisClient' crea y devuelve un cliente de Redis, utilizando la URI del servidor Redis que
está configurado en "localhost" y el puerto 6379, que es el valor por defecto para Redis.
*/

public class RedisConfig {
    public RedisClient prepareRedisClient() {
        return RedisClient
                .create(RedisURI.create("localhost", 6379));
    }
}