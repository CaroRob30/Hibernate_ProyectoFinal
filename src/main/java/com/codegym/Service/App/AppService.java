package com.codegym.Service.App;

import com.codegym.Domain.Country;
import com.codegym.Service.Country.CountryService;
import com.codegym.Service.Country.CountryTransformer;
import com.codegym.Service.DataBase.PerformanceTester;
import com.codegym.Service.Redis.RedisDataManager;
import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.Service.CityCountry.CityDataProcessor;
import com.codegym.Redis.CityCountry;
import org.hibernate.SessionFactory;

import java.util.List;

/*
En esta clase se gestiona el flujo principal de la aplicación, coordinando la obtención, procesamiento y
almacenamiento de datos, así como la ejecución de pruebas de rendimiento. En su constructor, recibe objetos
que permiten procesar datos de ciudades y países, gestionar operaciones con Redis, realizar pruebas de rendimiento
y transformar datos relacionados con los países. El método 'runApp' ejecuta el proceso completo, obteniendo y
transformando los datos de ciudades y países, almacenándolos en Redis y luego ejecutando pruebas de rendimiento
sobre una lista de identificadores.
Por último, cierra la sesión de la base de datos utilizando el 'SessionFactory'.
*/

public class AppService {
    private final CityDataProcessor cityDataProcessor;
    private final RedisDataManager redisDataManager;
    private final PerformanceTester performanceTester;
    private final CountryService countryService;
    private final CountryTransformer countryTransformer;
    private final SessionFactory sessionFactory;

    public AppService(CityDataProcessor cityDataProcessor, RedisDataManager redisDataManager,
                      PerformanceTester performanceTester, CountryService countryService,
                      CountryTransformer countryTransformer) {
        this.cityDataProcessor = cityDataProcessor;
        this.redisDataManager = redisDataManager;
        this.performanceTester = performanceTester;
        this.countryService = countryService;
        this.countryTransformer = countryTransformer;
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public void runApp() {

        List<CityCountry> preparedCityData = cityDataProcessor.getProcessedCityData();

        List<Country> countries = countryService.getAllCountries();
        List<CityCountry> preparedCountryData = countryTransformer.transformData(countries);

        redisDataManager.pushToRedis(preparedCityData);

        redisDataManager.pushToRedis(preparedCountryData);

        sessionFactory.getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);
        performanceTester.runPerformanceTests(ids);
    }
}
