package com.codegym.Domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

/*
En esta clase se representa un idioma hablado en un país y está mapeada a la tabla 'country_language'
dentro del esquema 'world'.
Esta clase también cuenta con varios atributos para identificar a qué país pertenece un idioma, el nombre del idioma,
si es oficial o no, y el porcentaje de la población que lo habla.
La clase utiliza anotaciones de JPA para definir las relaciones y el mapeo de los datos, como '@ManyToOne' para la
relación con 'Country', y '@Column' para especificar detalles como la definición del tipo de columna
y el comportamiento en la base de datos.
*/

@Entity
@Table(schema = "world", name = "country_language")
public class CountryLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String language;

    @Column(name = "is_official", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isOfficial;

    private BigDecimal percentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
