package com.nixs.util;

import com.nixs.model.Role;
import com.nixs.model.User;
import org.apache.logging.log4j.util.EnvironmentPropertySource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.RuleBasedCollator;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:tcp://db:1521/testdb;INIT=runscript from '/opt/sql/dml.sql'");
            configuration.setProperty("hibernate.connection.username", "sa");
            configuration.setProperty("hibernate.connection.password", "");

        try {
            return configuration.configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error creating SessionFactory", e);
        }
    }

    public static SessionFactory getInstance() {
        return instance;
    }

//    ---------------------------------------------------------------
//
//    private static SessionFactory instance = getInstance();
//    private static String userName = "sa";

//    private static String password = "";
//    private static String dbUrl = "jdbc:h2:./mydb;INIT=runscript from 'classpath:/database/sql/dml.sql'";
//
//    private HibernateUtil() {
//    }
//
//    public static SessionFactory getInstance() {
//        if (instance == null) {
//            Configuration configuration = new Configuration();
//            configuration.setProperty("hibernate.connection.username", userName);
//            configuration.setProperty("hibernate.connection.password", password);
//            configuration.setProperty("hibernate.connection.url", dbUrl);
//            configuration.addAnnotatedClass(Role.class);
//            configuration.addAnnotatedClass(User.class);
//            configuration.configure();
//            instance = configuration.buildSessionFactory();
//        }
//        return instance;
//    }

//------------------------------------------------------------------

//    public static SessionFactory getInstance() {
//        return instance;
//    }

//    private static SessionFactory instance = getInstance();
//
//    public static SessionFactory getInstance() {
//        if (instance == null) {
//            // loads configuration and mappings
//            Configuration configuration = new Configuration();
//            configuration.setProperty("hibernate.connection.url", "jdbc:h2:./mydb;DATABASE_TO_UPPER=false");
//            configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//            configuration.setProperty("hibernate.connection.username", "root");
//            configuration.setProperty("hibernate.connection.password", "root");
//            configuration.setProperty("hibernate.show_sql", "true");
//            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//
//            configuration.configure();
//            ServiceRegistry serviceRegistry
//                    = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//
//            // builds a session factory from the service registry
//            instance = configuration.buildSessionFactory(serviceRegistry);
//        }
//
//        return instance;
//    }


//    ----------------------------------------------

//    private static SessionFactory instance;
//
//    private HibernateUtil() {
//
//    }
//
//    public static SessionFactory getInstance() {
//        if (instance == null) {
//            Configuration configuration = new Configuration();
//            configuration.setProperty("hibernate.connection.url", System.getenv("JDBC_URL"));
//            configuration.setProperty("hibernate.connection.username", System.getenv("JDBC_USER"));
//            configuration.setProperty("hibernate.connection.password", System.getenv("JDBC_PASSWORD"));
//            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//            configuration.setProperty("connection.driver_class", "org.h2.Driver");
//            configuration.setProperty("show_sql", "true");
//            configuration.setProperty("hbm2ddl.auto", "update");
//            configuration.configure();
//            instance = configuration.buildSessionFactory();
//        }
//        return instance;
//    }
}
