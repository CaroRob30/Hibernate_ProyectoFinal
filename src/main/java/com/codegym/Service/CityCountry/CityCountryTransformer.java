package com.codegym.Service.CityCountry;

import com.codegym.Domain.City;
import com.codegym.Domain.Country;
import com.codegym.Domain.CountryLanguage;
import com.codegym.Redis.CityCountry;
import com.codegym.Redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Esta clase transforma una lista de objetos 'City' en una lista de objetos 'CityCountry'.
El método 'transform' itera sobre cada ciudad de la lista, y para cada ciudad, el método 'transformCityToCityCountry'
construye un nuevo objeto 'CityCountry' que contiene información tanto de la ciudad como del país asociado.
Para cada país, se recopilan datos como el código, el nombre, la población, la superficie, el continente,
y las lenguas habladas en ese país.
Las lenguas se convierten en objetos 'Language', y luego se asignan al objeto 'CityCountry'.
*/

public class CityCountryTransformer {

    public List<CityCountry> transform(List<City> cities) {
        return cities.stream().map(this::transformCityToCityCountry).collect(Collectors.toList());
    }

    private CityCountry transformCityToCityCountry(City city) {
        CityCountry res = new CityCountry();
        res.setId(city.getId());
        res.setName(city.getName());
        res.setPopulation(city.getPopulation());
        res.setDistrict(city.getDistrict());

        Country country = city.getCountry();
        res.setAlternativeCountryCode(country.getAlternativeCode());
        res.setContinent(country.getContinent());
        res.setCountryCode(country.getCode());
        res.setCountryName(country.getName());
        res.setCountryPopulation(country.getPopulation());
        res.setCountryRegion(country.getRegion());
        res.setCountrySurfaceArea(country.getSurfaceArea());

        Set<CountryLanguage> countryLanguages = country.getLanguages();
        Set<Language> languages = countryLanguages.stream().map(cl -> {
            Language language = new Language();
            language.setLanguage(cl.getLanguage());
            language.setOfficial(cl.getOfficial());
            language.setPercentage(cl.getPercentage());
            return language;
        }).collect(Collectors.toSet());
        res.setLanguages(languages);

        return res;
    }
}
