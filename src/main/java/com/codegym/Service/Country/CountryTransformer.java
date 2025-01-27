package com.codegym.Service.Country;

import com.codegym.domain.Country;
import com.codegym.domain.CountryLanguage;
import com.codegym.redis.CityCountry;
import com.codegym.redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CountryTransformer {

    public CountryTransformer() {
    }

    public List<CityCountry> transformData(List<Country> countries) {
        return countries.stream().map(country -> {
            CityCountry res = new CityCountry();
            res.setCountryCode(country.getCode());
            res.setCountryName(country.getName());
            res.setCountryPopulation(country.getPopulation());
            res.setCountryRegion(country.getRegion());
            res.setCountrySurfaceArea(country.getSurfaceArea());
            res.setAlternativeCountryCode(country.getAlternativeCode());
            res.setContinent(country.getContinent());

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
        }).collect(Collectors.toList());
    }
}
