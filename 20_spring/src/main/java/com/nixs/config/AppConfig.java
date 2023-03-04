package com.nixs.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.nixs")
@RequiredArgsConstructor
public class AppConfig {
    private final Environment environment;

    @Bean
    public DataSource getDataSource() {
        String connectionUrl = System.getenv("JDBC_URL");
        String connectionUsername = System.getenv("JDBC_USER");
        String connectionPassword = System.getenv("JDBC_PASSWORD");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));

        dataSource.setUrl(Objects.requireNonNullElse(
                connectionUrl, environment.getProperty("db.url")));

        dataSource.setUsername(Objects.requireNonNullElse(
                connectionUsername, environment.getProperty("db.username")));

        dataSource.setPassword(Objects.requireNonNullElse(
                connectionPassword, environment.getProperty("db.password")));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());

        Properties properties = new Properties();
        properties.put("show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        localSessionFactoryBean.setHibernateProperties(properties);

        localSessionFactoryBean.setPackagesToScan("com.nixs.model");

        return localSessionFactoryBean;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
