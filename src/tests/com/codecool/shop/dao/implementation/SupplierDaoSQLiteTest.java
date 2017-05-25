package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.HelpTestClass;
import com.codecool.shop.model.Supplier;
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
class SupplierDaoSQLiteTest {

    @BeforeEach
    void clearDB() throws SQLException {
        {
            HelpTestClass helpTestClass = new HelpTestClass();
            helpTestClass.ClearTableInDB("Supplier");
        }
    }

    @Test
    void testAdd() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        SupplierDaoSQLite supplierDaoSQLite = new SupplierDaoSQLite(conn);
        Supplier supplier = new Supplier("x","x");
        supplierDaoSQLite.add(supplier);
        assertEquals(1, supplierDaoSQLite.getAll().size());
    }

    @Test
    void TestFindByID() throws SQLException {
        HelpTestClass helpTestClass = new HelpTestClass();
        helpTestClass.fillDbSupplier("Supplier");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        SupplierDaoSQLite supplierDaoSQLite = new SupplierDaoSQLite(conn);
        assertEquals("id: 1, name: Mlekpol, description: Polish dairy products.", supplierDaoSQLite.find(1).toString());

    }

    @Test
    void testRemoveAndGetAll() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        SupplierDaoSQLite supplierDaoSQLite = new SupplierDaoSQLite(conn);
        Supplier supplier = new Supplier("x","x");
        supplierDaoSQLite.add(supplier);
        supplierDaoSQLite.remove(supplierDaoSQLite.getAll().get(supplierDaoSQLite.getAll().size()-1).getId());
        assertEquals(0, supplierDaoSQLite.getAll().size());
    }


}