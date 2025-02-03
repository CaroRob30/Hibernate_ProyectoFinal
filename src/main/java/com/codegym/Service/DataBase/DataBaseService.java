package com.codegym.Service.DataBase;

import com.codegym.Configuration.SessionFactoryProvider;
import org.hibernate.SessionFactory;

/*
Esta clase maneja la conexión y cierre de la sesión con la base de datos utilizando un 'SessionFactory'.
En su constructor, obtiene el 'SessionFactory' de un proveedor específico, 'SessionFactoryProvider'.
La clase tiene un método 'shutdown()' que se encarga de cerrar la sesión activa del 'SessionFactory' si está presente,
liberando así los recursos relacionados con la base de datos.
Este servicio es crucial para asegurar que las conexiones de la base de datos se gestionen de forma eficiente,
cerrándolas adecuadamente cuando ya no se necesiten.
*/

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
