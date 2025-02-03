package com.codegym.Service.Country;

import com.codegym.DAO.CountryDAO;

/*
Esta clase configura e inicializa un objeto de tipo 'CountryService'.
El método 'configureCountryService' crea instancias de 'CountryDAO' y 'CountryTransformer', que son necesarios
para el funcionamiento de 'CountryService'. Luego, utiliza estos objetos para instanciar 'CountryService',
pasando los dos objetos como parámetros al constructor de 'CountryService'. Finalmente, devuelve el objeto
'CountryService' configurado y listo para ser usado en otras partes de la aplicación.
*/

public class CountryServiceConfigurator {
    public CountryService configureCountryService() {
        CountryDAO countryDAO = new CountryDAO();
        CountryTransformer countryTransformer = new CountryTransformer();
        return new CountryService(countryDAO, countryTransformer);
    }
}
