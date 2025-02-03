package com.codegym.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*
Esta clase gestiona las sesiones de base de datos utilizando Hibernate.
Es la encargada de abrir y cerrar las sesiones, así como de ejecutar operaciones dentro de una transacción.
Tiene un método genérico 'executeInTransaction' que acepta una operación a realizar sobre la base de datos,
la ejecuta dentro de una transacción y se encarga de confirmar los cambios.
También incluye una interfaz funcional 'DatabaseOperation' que define la estructura de las operaciones
que pueden ser ejecutadas dentro de una sesión.
*/

public class DatabaseSessionManager {
    private final SessionFactory sessionFactory;

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