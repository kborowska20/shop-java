package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by morthan on 23.05.17.
 */
class ProductDaoSQLiteTest {

    @Test
    void testFindProduct() {
        DbConnector dbConnector = new DbConnector();
        Connection conn = dbConnector.getConnection();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(conn);
//        ProductCategory productCategory = new ProductCategory("Dairy", "test","test");
//        Supplier supplier= new Supplier("Mlekpol", "test");
//        Product product =  new Product(2,"Milk", 1.7f, "PLN", "testcription", productCategory, supplier);
        assertEquals("id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol", productDaoSQLite.find(2).toString());
    }


}