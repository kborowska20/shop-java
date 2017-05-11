package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

    private Connection connection;

    public DbConnector() {
        this.connection = connect();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/Products.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getConnection() {
        return connection;
    }
}
