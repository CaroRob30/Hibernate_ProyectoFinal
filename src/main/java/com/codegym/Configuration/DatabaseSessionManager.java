package com.codegym.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DatabaseSessionManager {
    private SessionFactory sessionFactory;

    public DatabaseSessionManager() {
        this.sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public <T> T executeInTransaction(DatabaseOperation<T> operation) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T result = operation.execute(session);
            session.getTransaction().commit();
            return result;
        }
    }

    @FunctionalInterface
    public interface DatabaseOperation<T> {
        T execute(Session session);
    }
}