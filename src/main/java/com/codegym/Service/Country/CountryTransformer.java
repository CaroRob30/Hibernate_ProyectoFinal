package com.codegym.Service.Country;

import com.codegym.Domain.Country;
import com.codegym.Domain.CountryLanguage;
import com.codegym.Redis.CityCountry;
import com.codegym.Redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Esta clase transforma una lista de objetos 'Country' en una lista de objetos 'CityCountry', adaptando sus datos
y estructuras.
El método 'transformData' toma una lista de países, la procesa con el método 'transformCountryToCityCountry',
que convierte un 'Country' en un 'CityCountry'. Durante la transformación, también se manejan los idiomas
relacionados con cada país, convirtiendo cada 'CountryLanguage' en un objeto 'Language' mediante el método
'transformCountryLanguageToLanguage'.
Esta clase facilita la conversión de datos entre diferentes representaciones de objetos, permitiendo una
estructuración y procesamiento más adecuado para otras operaciones en la aplicación.
*/

public class CountryTransformer {

    public CountryTransformer() {
    }

    public List<CityCountry> transformData(List<Country> countries) {
        return countries.stream()
                .map(this::transformCountryToCityCountry)
                .collect(Collectors.toList());
    }

    private CityCountry transformCountryToCityCountry(Country country) {
        CityCountry res = new CityCountry();
        res.setCountryCode(country.getCode());
        res.setCountryName(country.getName());
        res.setCountryPopulation(country.getPopulation());
        res.setCountryRegion(country.getRegion());
        res.setCountrySurfaceArea(country.getSurfaceArea());
        res.setAlternativeCountryCode(country.getAlternativeCode());
        res.setContinent(country.getContinent());

        Set<Language> languages = transformLanguages(country.getLanguages());
        res.setLanguages(languages);

        return res;
    }

    private Set<Language> transformLanguages(Set<CountryLanguage> countryLanguages) {
        return countryLanguages.stream()
                .map(this::transformCountryLanguageToLanguage)
                .collect(Collectors.toSet());
    }

    private Language transformCountryLanguageToLanguage(CountryLanguage countryLanguage) {
        Language language = new Language();
        language.setLanguage(countryLanguage.getLanguage());
        language.setOfficial(countryLanguage.getOfficial());
        language.setPercentage(countryLanguage.getPercentage());
        return language;
    }
}