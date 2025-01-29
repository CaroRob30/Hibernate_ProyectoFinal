package com.codegym.Service.App;

import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.Service.City.CityService;
import com.codegym.Service.City.CityTestService;
import com.codegym.Service.CityCountry.CityCountryTransformer;
import com.codegym.Service.Redis.RedisService;
import com.codegym.domain.City;
import com.codegym.redis.CityCountry;
import org.hibernate.SessionFactory;

import java.util.List;

public class AppService {
    private final CityService cityService;
    private final CityCountryTransformer cityCountryTransformer;
    private final RedisService redisService;
    private final CityTestService cityTestService;
    private final SessionFactory sessionFactory;

    public AppService(CityService cityService, CityCountryTransformer cityCountryTransformer, RedisService redisService,
                      CityTestService cityTestService) {
        this.cityService = cityService;
        this.cityCountryTransformer = cityCountryTransformer;
        this.redisService = redisService;
        this.cityTestService = cityTestService;
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public void runApp() {
        // Obtener los datos de las ciudades
        List<City> allCities = cityService.fetchData();

        // Transformar los datos de las ciudades
        List<CityCountry> preparedData = cityCountryTransformer.transform(allCities);

        // Enviar los datos a Redis
        redisService.pushDataToRedis(preparedData);

        // Cerrar la sesión de Hibernate
        sessionFactory.getCurrentSession().close();

        // Pruebas con Redis y MySQL
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisService.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        cityTestService.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
    }
}
