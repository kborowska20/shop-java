package com.codecool.shop.controller;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by morthan on 25.05.17.
 */
class BaseControllerTest {
    @Test
    void testGetConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductController productController = new ProductController(conn);
        assertEquals(conn, productController.getConn());
    }

}