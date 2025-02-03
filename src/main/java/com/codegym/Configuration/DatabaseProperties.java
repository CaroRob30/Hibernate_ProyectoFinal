package com.codegym.Configuration;

import org.hibernate.cfg.Environment;

import java.util.Properties;
/*
Esta clase se encarga de configurar y retornar un objeto Properties con las configuraciones necesarias
para la conexión a una base de datos MySQL utilizando Hibernate y P6Spy.
Incluye configuraciones como el dialecto de MySQL, el driver JDBC, la URL de conexión, las credenciales
de acceso y las opciones relacionadas con la sesión y el batch de SQL.
Su función es gestionar la conexión y las operaciones relacionadas con la base de datos.
*/

public class DatabaseProperties {
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3307/world");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.STATEMENT_BATCH_SIZE, "100");

        return properties;
    }
}