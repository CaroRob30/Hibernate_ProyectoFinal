package com.codegym.Service.App;

import com.codegym.Configuration.RedisConfig;
import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.Service.City.CityService;
import com.codegym.Service.City.CityTestService;
import com.codegym.Service.CityCountry.CityCountryTransformer;
import com.codegym.Service.Country.CountryService;
import com.codegym.Service.DataBase.DataBaseService;
import com.codegym.Service.Redis.RedisService;
import com.codegym.dao.CityDAO;
import com.codegym.dao.CountryDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;

public class AppInitializer {
    public AppService initializeApp() {
        RedisConfig redisClient = new RedisConfig();
        ObjectMapper mapper = new ObjectMapper();
        CityDAO cityDAO = new CityDAO();
        CountryDAO countryDAO = new CountryDAO(SessionFactoryProvider.getSessionFactory());

        CityService cityService = new CityService(cityDAO);
        CityTestService cityTestService = new CityTestService(cityDAO);
        CityCountryTransformer cityCountryTransformer = new CityCountryTransformer();
        RedisService redisService = new RedisService(redisClient, mapper);

        return new AppService(cityService, cityCountryTransformer, redisService, cityTestService);
    }
}


