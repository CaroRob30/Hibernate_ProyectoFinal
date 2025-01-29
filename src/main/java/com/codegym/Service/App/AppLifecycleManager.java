package com.codegym.Service.App;

import com.codegym.Configuration.RedisConfig;
import com.codegym.Service.DataBase.DataBaseService;

import static java.util.Objects.nonNull;

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
