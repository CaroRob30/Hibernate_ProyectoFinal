package com.codegym.Configuration;

import com.codegym.domain.City;
import com.codegym.domain.Country;
import com.codegym.domain.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
