package com.free.parking.config.spring.database;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * Created by veladii on 2/12/17.
 */
@PropertySource("classpath:gradle.properties")
public class DataBaseConfig {

    @Autowired
    protected Environment environment;

    Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("db.dialect"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
