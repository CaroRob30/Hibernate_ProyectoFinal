package com.codegym.Service.City;

import com.codegym.DAO.CityDAO;
import com.codegym.Service.CityCountry.CityCountryTransformer;
import com.codegym.Service.CityCountry.CityDataProcessor;

/*
Esta clase configura y crea una instancia de servicio 'CityService'.
Primero se crea un objeto 'CityDAO' para acceder a los datos de las ciudades.
Luego, se generan instancias de otros servicios necesarios, como 'CityCountryTransformer' y 'CityDataProcessor',
que se utilizan dentro de 'CityService' para procesar y transformar los datos de las ciudades.
Inicialmente, 'CityService' se construye con un 'null' como 'CityDataProcessor', pero luego se actualiza correctamente
con una instancia de 'CityDataProcessor' antes de devolver el servicio 'CityService' completamente configurado.
*/

public class CityServiceConfigurator {
    public CityService configureCityService() {
        CityDAO cityDAO = new CityDAO();

        CityService cityService = new CityService(cityDAO, null);

        CityCountryTransformer cityCountryTransformer = new CityCountryTransformer();
        CityDataProcessor cityDataProcessor = new CityDataProcessor(cityService, cityCountryTransformer);

        cityService = new CityService(cityDAO, cityDataProcessor);

        return cityService;
    }
}
