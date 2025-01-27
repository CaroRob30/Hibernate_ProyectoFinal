package com.codegym.dao;

import com.codegym.Configuration.DatabaseSessionManager;
import com.codegym.Configuration.SessionFactoryProvider;
import com.codegym.domain.City;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {

    private final DatabaseSessionManager session;

    public CityDAO() {
        SessionFactoryProvider.getSessionFactory();
        this.session = new DatabaseSessionManager();
    }
    public List<City> getItems(int offset, int limit) {
        return session.executeInTransaction(session -> {
            Query<City> query = session.createQuery("select c from City c", City.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        });
    }
    public int getTotalCount() {
        return session.executeInTransaction(session -> {
            Query<Long> query = session
                    .createQuery("select count(c) from City c", Long.class);
            return Math.toIntExact(query.uniqueResult());
        });
    }

    public City getById(Integer id) {
        return session.executeInTransaction(session -> {
            Query<City> query = session
                    .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
            query.setParameter("ID", id);
            return query.getSingleResult();
        });
    }
}