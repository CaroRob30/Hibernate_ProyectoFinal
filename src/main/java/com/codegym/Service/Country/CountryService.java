package com.codegym.Service.Country;

import com.codegym.DAO.CountryDAO;
import com.codegym.Domain.Country;

import java.util.List;

/*
Esta clase gestiona la obtención y trasfomación de los datos de los países.
Utiliza el objeto 'CountryDAO' para recuperar una lista de todos los países desde la base de datos a través del
método 'getAllCountries'.
Además, tiene un transformador 'CountryTransformer' al que se puede acceder mediante el método 'getCountryTransformer',
el cual se puede utilizar para transformar los datos de los países cuando sea necesario.
*/

public class CountryService {
    private final CountryDAO countryDAO;
    private final CountryTransformer countryTransformer;

    public CountryService(CountryDAO countryDAO, CountryTransformer countryTransformer) {
        this.countryDAO = countryDAO;
        this.countryTransformer = countryTransformer;
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public CountryTransformer getCountryTransformer() {
        return countryTransformer;
    }
}
