package com.codegym.Service.DataBase;

import com.codegym.DAO.CityDAO;
import com.codegym.Service.City.CityDataFetcher;
import com.codegym.Service.City.CityDataTester;
import com.codegym.Service.City.CityTestService;
import com.codegym.Service.Redis.RedisDataManager;

/*
Esta clase configura y crea una instancia de 'PerformanceTester'.
El método 'configurePerformanceTester(RedisDataManager redisDataManager)' inicializa las dependencias necesarias:
'CityDataFetcher' (usando 'CityDAO'), 'CityDataTester' y 'CityTestService'.
Luego, crea y devuelve un 'PerformanceTester' con 'redisDataManager' y 'cityTestService', permitiendo realizar pruebas
de rendimiento comparando Redis y MySQL.
*/

public class PerformanceTesterConfigurator {

    public PerformanceTester configurePerformanceTester(RedisDataManager redisDataManager) {

        CityDataFetcher cityDataFetcher = new CityDataFetcher(new CityDAO());
        CityDataTester cityDataTester = new CityDataTester();
        CityTestService cityTestService = new CityTestService(cityDataFetcher, cityDataTester);

        return new PerformanceTester(redisDataManager, cityTestService);
    }
}
