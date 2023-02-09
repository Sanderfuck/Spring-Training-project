package com.nixs.service;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DbService {
    private static final String dbPropertiesPath = "database/properties/database.properties";
    private static final Logger logger = LogManager.getLogger(DbService.class);
    private BasicDataSource dataSource;

    public Connection getConnection() {
        logger.info("GetConnection method was called");
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error("Connection failed");
            throw new RuntimeException("Connection failed ", e);
        }
    }

    private BasicDataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        configureDataSource();
        return dataSource;
    }

    private void configureDataSource() {
        Properties properties = getProperties();
        String jdbc_url = System.getenv("JDBC_URL");

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(Objects.nonNull(jdbc_url) ? jdbc_url : properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));

        logger.info(dataSource.getUrl(), "URL uses for database: ");
    }

    private Properties getProperties() {
        logger.info("GetProperty was called");
        Properties properties = new Properties();

        try (InputStream inputStream =
                     BasicDataSource.class.getClassLoader().getResourceAsStream(dbPropertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Properties load failed");
            throw new RuntimeException("Properties load failed ", e);
        }
        return properties;
    }
}
