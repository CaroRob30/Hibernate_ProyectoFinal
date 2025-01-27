package org.example;

import com.codegym.Configuration.RedisConfigManager;
import com.codegym.Configuration.RedisConnectionManager;
import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.Service.City.CityService;
import com.codegym.Service.City.CityTestService;
import com.codegym.Service.Country.CountryService;
import com.codegym.Service.Country.CountryTransformer;
import com.codegym.Service.DataBase.DataBaseService;
import com.codegym.Service.Redis.RedisDataFetcher;
import com.codegym.Service.Redis.RedisDataPusher;
import com.codegym.Service.Redis.RedisDataTester;
import com.codegym.Service.Redis.RedisService;
import com.codegym.dao.CityDAO;
import com.codegym.dao.CountryDAO;
import com.codegym.domain.City;
import com.codegym.domain.Country;
import com.codegym.redis.CityCountry;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.LogManager;

public class Main {
    private DataBaseService dataBaseService;
    private CityService cityService;
    private CityTestService cityTestService;
    private CountryService countryService;
    private CountryTransformer countryTransformer;
    private final SessionFactory sessionFactory;
    private final RedisConnectionManager redisConnection;
    private final RedisDataPusher redisDataPusher;
    private final RedisDataTester redisDataTester;

    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    public Main() {
        dataBaseService = new DataBaseService();

        sessionFactory = SessionFactoryProvider.getSessionFactory();
        cityDAO = new CityDAO();
        cityService = new CityService(cityDAO);
        cityTestService = new CityTestService(cityDAO);
        countryDAO = new CountryDAO();
        countryService = new CountryService(countryDAO);
        countryTransformer = new CountryTransformer();

        redisConnection = new RedisConnectionManager(new RedisConfigManager());
        ObjectMapper mapper = new ObjectMapper();
        redisDataPusher = new RedisDataPusher(redisConnection.getRedisConnection(), mapper);
        redisDataTester = new RedisDataTester(
                new RedisDataFetcher(new RedisService(redisConnection.getRedisConnection())));
        mapper = new ObjectMapper();

    }

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main main = new Main();
        List<City> allCities = main.cityService.fetchData();
        List<Country> allCountries = main.countryService.getAllCountries();
        List<CityCountry> preparedData = main
                .countryTransformer.transformData(allCountries);
        main.redisDataPusher.pushToRedis(preparedData);

        // close the current session in order to make a query to the database for sure, and not to pull data from the cache
        main.sessionFactory.getCurrentSession().close();

        //choose random 10 id cities
        //since we did not handle invalid situations, use the existing id in the database
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        main.redisDataTester.fetchRedisData(ids);

        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        main.cityTestService.testMysqlData(ids);

        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        main.dataBaseService.shutdown();
        main.redisConnection.redisShutdown();

    }
}