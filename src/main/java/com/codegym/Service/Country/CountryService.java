package com.codegym.Service.Country;

import com.codegym.dao.CountryDAO;
import com.codegym.domain.Country;

import java.util.List;

public class CountryService {
    private final CountryDAO countryDAO;

    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }
}
