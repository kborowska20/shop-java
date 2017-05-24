package com.codecool.shop.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelpTestClass {

    public void clearAllDb(String tableName) throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        Statement dbStatement = conn.createStatement();
        String query = "DELETE FROM " + tableName+" ;";
        dbStatement.execute(query);
        dbStatement.close();

    }
    public void fillDB(String tableName) throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        Statement dbStatement = conn.createStatement();
        String query = "INSERT INTO "+ tableName +" (id,name,department,description) VALUES " +
                "(1,'Fruit','Food','The best the Earth has given us.'),\n" +
                " (2,'Dairy','Food','Stolen from under a cow''s nose.'),\n" +
                " (3,'Pastries','Food','Tasty and fresh.'),\n" +
                " (4,'Meat','Food','Always fresh.'),\n" +
                " (5,'Processed','Food','Forever fresh.');";
        dbStatement.execute(query);
        dbStatement.close();

    }
}


