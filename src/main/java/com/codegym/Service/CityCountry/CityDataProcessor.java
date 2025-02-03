package com.codegym.Service.CityCountry;

import com.codegym.Domain.City;
import com.codegym.Redis.CityCountry;
import com.codegym.Service.City.CityService;

import java.util.List;

/*
Esta clase procesa la información de las ciudades.
Utiliza el servicio 'CityService' para obtener una lista completa de todas las ciudades y luego transforma
esa lista en una representación más detallada utilizando el 'CityCountryTransformer'.
El método 'getProcessedCityData' obtiene las ciudades a través de 'cityService.fetchData()' y las convierte en objetos
'CityCountry' mediante el transformador, devolviendo así una lista de 'CityCountry' procesada y lista para su uso.
Esta clase centraliza el proceso de obtención y transformación de datos de ciudades.
*/

public class CityDataProcessor {
    private final CityService cityService;
    private final CityCountryTransformer cityCountryTransformer;

    public CityDataProcessor(CityService cityService, CityCountryTransformer cityCountryTransformer) {
        this.cityService = cityService;
        this.cityCountryTransformer = cityCountryTransformer;
    }

    public List<CityCountry> getProcessedCityData() {
        List<City> allCities = cityService.fetchData();
        return cityCountryTransformer.transform(allCities);
    }
}
