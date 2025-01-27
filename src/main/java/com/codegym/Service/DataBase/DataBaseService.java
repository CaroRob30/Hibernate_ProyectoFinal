package com.codegym.Service.DataBase;

import com.codegym.Configuration.SessionFactoryProvider;
import org.hibernate.SessionFactory;

public class DataBaseService {
    private final SessionFactory sessionFactory;

    public DataBaseService() {
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
