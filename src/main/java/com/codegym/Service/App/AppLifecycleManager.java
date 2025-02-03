package com.codegym.Service.App;

import com.codegym.Configuration.RedisConfig;
import com.codegym.Service.DataBase.DataBaseService;

import static java.util.Objects.nonNull;

/*
Esta clase gestiona el ciclo de vida de la aplicación, controlando la incialización y la finalización de los
servicios esenciales. Recibe las instancias 'AppService', 'DataBaseService' y 'RedisConfig' para operar.
El método 'startApp' inicia la aplicación llamando al método 'runApp' de 'AppService'. Por otro lado, el método
'shutdown' se encarga de cerrar los servicios de la base de datos y, si es necesario, también apaga el cliente de
Redis, asegurando que todos los recursos se liberen correctamente al finalizar la ejecución de la aplicación.
*/

public class AppLifecycleManager {
    private final AppService appService;
    private final DataBaseService dataBaseService;
    private final RedisConfig redisClient;

    public AppLifecycleManager(AppService appService, DataBaseService dataBaseService, RedisConfig redisClient) {
        this.appService = appService;
        this.dataBaseService = dataBaseService;
        this.redisClient = redisClient;
    }

    public void startApp() {
        appService.runApp();
    }

    public void shutdown() {
        dataBaseService.shutdown();
        if (nonNull(redisClient)) {
            redisClient.prepareRedisClient().shutdown();
        }
    }
}
