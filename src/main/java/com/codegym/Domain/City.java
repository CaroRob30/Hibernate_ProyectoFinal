package com.codegym.Domain;

import jakarta.persistence.*;

/*
Esta clase representa una entidad que corresponde a la tabla 'city' en el esquema 'world' de la base de datos.
Utiliza la anotación '@Entity' para que sea gestionada por Hibernate y se utiliza '@Table' para especificar
el nombre de la tabla y el esquema correspondiente.
Cuenta con varios atributos como 'id', 'name', 'country', 'district' y 'population', que mapean a las columnas
de la tabla 'city'.
La relación entre 'City' y 'Country0 es de tipo 'ManyToOne', por lo que muchas ciudades pueden estar asociadas
con un solo país, y esta relación se maneja mediante la anotación 'JoinColumn'.
De igual forma, la clase también incluye los métodos getter y setter para acceder y modificar sus atributos de
ser necesario.
*/

@Entity
@Table(schema = "world", name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String district;

    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
