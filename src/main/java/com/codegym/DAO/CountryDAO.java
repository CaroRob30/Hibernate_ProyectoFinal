package com.codegym.DAO;

import com.codegym.Configuration.DatabaseSessionManager;
import com.codegym.Domain.Country;
import org.hibernate.query.Query;

import java.util.List;

/*
Esta clase se encarga de manejar las operaciones de acceso a la base de datos relacionadas con la entidad Country.
Utiliza 'DatabaseSessionManager' para gestionar las sesiones de Hibernate y ejecutar transacciones.
Actualmente solo tiene un método 'getAll' que recupera todas las instancias de Country junto con sus idiomas
asociados mediante una consulta HQL que realiza un 'join fetch' para cargar de manera eficiente la relación
entre Country y Language en una sola operación
*/

public class CountryDAO {

    private final DatabaseSessionManager session;

    public CountryDAO() {
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