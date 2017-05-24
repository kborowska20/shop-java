package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by morthan on 23.05.17.
 */
class ProductDaoSQLiteTest {


    @Test
    void testFindProduct() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(conn);
        assertEquals("id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN," +
                " productCategory: Dairy, supplier: Mlekpol", productDaoSQLite.find(2).toString());
    }

    @Test
    void testGetByCategory() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(conn);
        ProductCategory productCategory = new ProductCategory(1, "Test", "Test", "Test");
        assertEquals("[id: 3, name: Bananas, defaultPrice: 3,10, defaultCurrency: PLN, productCategory: Test," +
                        " supplier: Boongaboonga, id: 6, name: Apples, defaultPrice: 1,40, defaultCurrency: PLN," +
                        " productCategory: Test, supplier: PolSad, id: 9, name: Tomatoes, defaultPrice: 3,70, " +
                        "defaultCurrency: PLN, productCategory: Test, supplier: PolSad]",
                productDaoSQLite.getBy(productCategory).toString());

    }

    @Test
    void testGetBySupplier() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(conn);
        Supplier supplier = new Supplier(1, "Test", "Test");
        assertEquals("[id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy," +
                        " supplier: Test, id: 4, name: Butter, defaultPrice: 4,20, defaultCurrency: PLN," +
                        " productCategory: Dairy, supplier: Test, id: 7, name: White cheese, defaultPrice: 1,80," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Test]",
                productDaoSQLite.getBy(supplier).toString());
    }
}