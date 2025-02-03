package com.codegym.Service.City;

import com.codegym.DAO.CityDAO;
import com.codegym.Domain.City;
import com.codegym.Service.CityCountry.CityDataProcessor;

import java.util.ArrayList;
import java.util.List;

/*
Esta clase maneja la lógica relacionada con las ciudades.
Tiene dos dependencias principales: 'CityDAO', que proporciona acceso a los datos de las ciudades,
y 'CityDataProcessor', que se utiliza para procesar los datos.
El método 'fetchData' recupera todos los registros de ciudades de la base de datos en bloques de 500
ciudades a la vez, utilizando la paginación para obtener la lista completa.
Una vez que los datos se recuperan, se agregan a una lista 'allCities' y se devuelven.
Además, proporciona un getter para obtener el 'CityDataProcessor'.
 */

public class CityService {
    private final CityDAO cityDAO;
    private final CityDataProcessor cityDataProcessor;

    public CityService(CityDAO cityDAO, CityDataProcessor cityDataProcessor) {
        this.cityDAO = cityDAO;
        this.cityDataProcessor = cityDataProcessor;

    }

    public List<City> fetchData() {
        List<City> allCities = new ArrayList<>();
        int totalCount = cityDAO.getTotalCount();
        int step = 500;
        for (int i = 0; i < totalCount; i += step) {
            allCities.addAll(cityDAO.getItems(i, step));
        }
        return allCities;
    }

    public CityDataProcessor getCityDataProcessor() {
        return cityDataProcessor;
    }
}

