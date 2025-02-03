package com.codegym.Service.City;

import com.codegym.DAO.CityDAO;
import com.codegym.Domain.City;

import java.util.List;
import java.util.stream.Collectors;

/*
Esta clase es la responsable de obtener información sobre ciudades desde la base de datos utilizando un
objeto 'CityDAO'. En su constructor, recibe una instancia de 'CityDAO' y utiliza su método 'getById' para
recuperar ciudades por su identificador. El método 'fetchCitiesByIds' toma una lista de identificadores de
ciudades, los mapea a sus correspondientes objetos 'City' y devuelve una lista de ciudades obtenidas desde la
base de datos.
*/

public class CityDataFetcher {
    private final CityDAO cityDAO;

    public CityDataFetcher(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> fetchCitiesByIds(List<Integer> ids) {
        return ids.stream()
                .map(cityDAO::getById)
                .collect(Collectors.toList());
    }
}
