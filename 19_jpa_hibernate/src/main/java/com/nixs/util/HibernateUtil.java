package com.nixs.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = getConfiguration();

        try {
            return configuration.configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error creating SessionFactory", e);
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        String connectionUrl = System.getenv("JDBC_URL");
        String connectionUsername = System.getenv("JDBC_USER");
        String connectionPassword = System.getenv("JDBC_PASSWORD");

        if (connectionUrl != null) {
            configuration.setProperty("hibernate.connection.url", connectionUrl);
        } else {
            configuration.setProperty("hibernate.connection.url",
                    "jdbc:h2:./mydb;INIT=runscript from 'classpath:/database/sql/dml.sql'");
        }

        if (connectionUsername != null) {
            configuration.setProperty("hibernate.connection.username", connectionUsername);
        } else {
            configuration.setProperty("hibernate.connection.username", "root");
        }

        if (connectionPassword != null) {
            configuration.setProperty("hibernate.connection.password", connectionPassword);
        } else {
            configuration.setProperty("hibernate.connection.password", "root");
        }
        return configuration;
    }

    public static SessionFactory getInstance() {
        return instance;
    }
}
