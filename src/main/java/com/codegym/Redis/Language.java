package com.codegym.Redis;

import java.math.BigDecimal;

/*
 Esta clase representa un idioma hablado en un país, con atributos que describen el nombre del idioma,
si es oficial en el país (a través del atributo 'isOfficial'), y el porcentaje de la población que lo habla
(almacenado en 'percentage'). Es una estructura sencilla utilizada para almacenar y manipular la información
relacionada con los idiomas dentro del contexto de un país, y está pensada para ser utilizada en combinación
con otras entidades como 'Country' o 'CountryLanguage'.
 */
public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;

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
