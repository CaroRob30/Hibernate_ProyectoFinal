package com.codegym.Service.DataBase;

import com.codegym.Service.Redis.RedisDataManager;
import com.codegym.Service.City.CityTestService;

import java.util.List;

/*
Esta clase evalúa el rendimiento de la recuperación de datos desde Redis y MySQL.
Recibe como dependencias un 'RedisDataManager' y un 'CityTestService'.
Su método 'runPerformanceTests(List<Integer> ids)' mide el tiempo de ejecución de la obtención de datos
desde Redis y MySQL, calculando el tiempo de inicio y fin de cada operación.
Finalmente, imprime los resultados en milisegundos, permitiendo comparar el tiempo que toma acceder a los datos
desde cada fuente.
*/

public class PerformanceTester {
    private final RedisDataManager redisDataManager;
    private final CityTestService cityTestService;

    public PerformanceTester(RedisDataManager redisDataManager, CityTestService cityTestService) {
        this.redisDataManager = redisDataManager;
        this.cityTestService = cityTestService;
    }

    public void runPerformanceTests(List<Integer> ids) {
        long startRedis = System.currentTimeMillis();
        redisDataManager.testRedis(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        cityTestService.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
    }
}
