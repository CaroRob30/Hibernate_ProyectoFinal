package com.codegym.dao;

import com.codegym.Configuration.DatabaseSessionManager;
import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.domain.Country;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO {

    private final DatabaseSessionManager session;

    public CountryDAO() {
     SessionFactoryProvider.getSessionFactory();
        this.session = new DatabaseSessionManager();
    }

    public List<Country> getAll() {
        return session.executeInTransaction(session -> {
            Query<Country> query = session
                    .createQuery("select c from Country c join fetch c.languages", Country.class);
            return query.list();
        });
    }
}