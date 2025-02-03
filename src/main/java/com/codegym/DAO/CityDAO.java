package com.codegym.DAO;

import com.codegym.Configuration.DatabaseSessionManager;
import com.codegym.Domain.City;
import org.hibernate.query.Query;

import java.util.List;

/*
Esta clase es la responsable de interactuar con la base de datos para realizar operaciones CRUD específicas
de la entidad City. Utiliza 'DatabaseSessionManager' para manejar las sesiones de Hibernate y ejecutar las
transacciones de manera segura. Cuenta con métodos como 'getItems' para obtener una lista paginada de ciudades,
'getTotalCount' para obtener el número total de ciudades, y 'getById' para recuperar una ciudad por su ID,
incluyendo su relación con el país. Todos estos métodos se ejecutan dentro de transacciones gestionadas
automáticamente, garantizando consistencia y manejo de errores.
*/

public class CityDAO {
    private final DatabaseSessionManager session;

    public CityDAO() {
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