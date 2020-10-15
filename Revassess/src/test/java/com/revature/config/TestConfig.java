package com.revature.config;

import java.sql.Connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestConfig{
    
    private static SessionFactory sesfact;

    private static SessionFactory getFactory(){
        return new Configuration()
        .setProperty("hibernate.connection.url", ConnectionUtil.URL)
        .setProperty("hibernate.connection.username", ConnectionUtil.USERNAME)
        .setProperty("hibernate.connection.password", ConnectionUtil.PASSWORD)
        .setProperty("hibernate.connection.pool_size", "3")
        .setProperty("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_SERIALIZABLE))
        .setProperty("hibernate.hbm2ddl.auto", "none")
        .setProperty("hibernate.show_sql", "true")
        .buildSessionFactory();   
    }

    public static SessionFactory getInstance(){
        if(sesfact == null){
            sesfact = getFactory();
        }
        return sesfact;
    }

    private TestConfig(){}
}