package com.codegym.Service.City;

import com.codegym.Domain.City;

import java.util.List;

/*
Esta clase es la encargada de gestionar la obtención y prueba de datos de ciudades desde una base de datos MySQL.
Utiliza un 'CityDataFetcher' para obtener las ciudades correspondientes a los identificadores proporcionados
en la lista 'ids'. Después, por cada ciudad recuperada, utiliza un 'CityDataTester' para ejecutar las pruebas
sobre los datos de esa ciudad, facilitando la validación de los datos de las ciudades a través de pruebas específicas.
*/

public class CityTestService {
    private final CityDataFetcher cityDataFetcher;
    private final CityDataTester cityDataTester;

    public CityTestService(CityDataFetcher cityDataFetcher, CityDataTester cityDataTester) {
        this.cityDataFetcher = cityDataFetcher;
        this.cityDataTester = cityDataTester;
    }

    public void testMysqlData(List<Integer> ids) {
        List<City> cities = cityDataFetcher.fetchCitiesByIds(ids);
        for (City city : cities) {
            cityDataTester.testCityData(city);
        }
    }
}
