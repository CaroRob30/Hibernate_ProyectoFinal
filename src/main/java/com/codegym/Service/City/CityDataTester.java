package com.codegym.Service.City;

import com.codegym.Domain.City;

/*
Esta clase realiza pruebas sobre los datos de una ciudad.
Su método 'testCityData' recibe un objeto 'City' y accede a los idiomas asociados al país de esa ciudad.
La finalidad de esta clase es verificar que los datos relacionados con la ciudad y su país
(como los idiomas) se puedan recuperar correctamente.
No realiza ninguna operación adicional o validación, sino que simplemente accede a los datos de la ciudad
a través de las relaciones definidas en el modelo de datos.
*/

public class CityDataTester {

    public void testCityData(City city) {
        city.getCountry().getLanguages();
    }
}
