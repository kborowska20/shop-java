package com.codecool.shop.dao;

import java.sql.*;

/**
 * Created by kamil on 25.04.17.
 */
public class DbConnector {

    public DbConnector() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/Products.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}