package com.codegym.Configuration;

import com.codegym.Domain.City;
import com.codegym.Domain.Country;
import com.codegym.Domain.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
Esta clase proporciona una instancia única y compartida de `SessionFactory` para la configuración de Hibernate,
garantizando que la misma instancia de la sesión se utilice en toda la aplicación.
La clase implementa el patrón Singleton con doble comprobación de bloqueo para asegurar que solo se cree una
instancia de `SessionFactory` y que sea accesible de manera segura en entornos multihilo.
El método 'getSessionFactory' configura la sesión utilizando las clases de entidad 'City', 'Country' y
'CountryLanguage', así como los detalles de configuración de la base de datos que se proporcionan en la clase
'DatabaseProperties'.
 */

public class SessionFactoryProvider {
    private static volatile SessionFactory sessionFactory;

    private SessionFactoryProvider() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (SessionFactoryProvider.class) {
                if (sessionFactory == null) {
                    DatabaseProperties dbProperties = new DatabaseProperties();
                    sessionFactory = new Configuration()
                            .addAnnotatedClass(City.class)
                            .addAnnotatedClass(Country.class)
                            .addAnnotatedClass(CountryLanguage.class)
                            .addProperties(dbProperties.getProperties())
                            .buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}