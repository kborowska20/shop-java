package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by oskar on 25.05.17.
 */
class ProductCategoryDaoSQLiteTest {


    @Test
    void testFind() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSQLite(conn);
        assertEquals("id: 1, name: Fruit, department: Food, description: The best the Earth has given us.",productCategoryDao.find(1).toString());
    }

}