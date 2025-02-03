package com.codegym.Service.App;

import com.codegym.Service.City.CityService;
import com.codegym.Service.City.CityServiceConfigurator;
import com.codegym.Service.Country.CountryService;
import com.codegym.Service.Country.CountryServiceConfigurator;
import com.codegym.Service.DataBase.PerformanceTester;
import com.codegym.Service.DataBase.PerformanceTesterConfigurator;
import com.codegym.Service.Redis.RedisDataManager;
import com.codegym.Service.Redis.RedisServiceConfigurator;
/*
Esta clase se encarga de la configuración de los servicios necesarios para la aplicación,
delegando la responsabilidad de configuración a otras clases. Utiliza las clases 'CityServiceConfigurator',
'CountryServiceConfigurator', 'RedisServiceConfigurator' y 'PerformanceTesterConfigurator' para configurar
servicios relacionados con las ciudades, los países, Redis y las pruebas de rendimiento.
Los métodos proporcionados por esta clase permiten obtener las instancias configuradas de cada
uno de estos servicios para ser utilizados en la inicialización de la aplicación.
*/

public class AppConfigurator {

    private final CityServiceConfigurator cityServiceConfigurator;
    private final CountryServiceConfigurator countryServiceConfigurator;
    private final RedisServiceConfigurator redisServiceConfigurator;
    private final PerformanceTesterConfigurator performanceTesterConfigurator;

    public AppConfigurator() {
        this.cityServiceConfigurator = new CityServiceConfigurator();
        this.countryServiceConfigurator = new CountryServiceConfigurator();
        this.redisServiceConfigurator = new RedisServiceConfigurator();
        this.performanceTesterConfigurator = new PerformanceTesterConfigurator();
    }

    public CityService configureCityService() {
        return cityServiceConfigurator.configureCityService();
    }

    public CountryService configureCountryService() {
        return countryServiceConfigurator.configureCountryService();
    }

    public RedisDataManager configureRedisService() {
        return redisServiceConfigurator.configureRedisService();
    }

    public PerformanceTester configPerformTester(RedisDataManager redisDataManager) {
        return performanceTesterConfigurator.configurePerformanceTester(redisDataManager);
    }
}
