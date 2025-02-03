package com.codegym.Service.App;

import com.codegym.Service.City.*;
import com.codegym.Service.Country.CountryService;

import com.codegym.Service.DataBase.PerformanceTester;
import com.codegym.Service.Redis.RedisDataManager;

/*
Esta clase se encarga de inicializar la aplicación, configurando los diferentes servicios necesarios
utilizando una instancia de 'AppConfigurator'. La clase configura los servicios relacionados con las ciudades,
países, Redis y las pruebas de rendimiento. Luego, crea una instancia de 'AppService', pasándole las instancias
configuradas de estos servicios y sus respectivos componentes. De esta manera, la clase organiza y prepara los
elementos esenciales para el funcionamiento de la aplicación.
*/

public class AppInitializer {

    private final AppConfigurator appConfigurator;

    public AppInitializer() {
        this.appConfigurator = new AppConfigurator();
    }

    public AppService initializeApp() {
        CityService cityService = appConfigurator.configureCityService();
        CountryService countryService = appConfigurator.configureCountryService();
        RedisDataManager redisDataManager = appConfigurator.configureRedisService();

        PerformanceTester performanceTester = appConfigurator
                .configPerformTester(redisDataManager);

        return new AppService(cityService.getCityDataProcessor(), redisDataManager, performanceTester,
                countryService, countryService.getCountryTransformer());
    }
}
