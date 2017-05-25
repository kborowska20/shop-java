package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
    @org.junit.jupiter.api.Test
    void testGetDescription() {
        Supplier supplier = new Supplier("SupplierName","supplierDisc");
        assertEquals("supplierDisc", supplier.getDescription() );
    }
    @org.junit.jupiter.api.Test
    void testGetAndSetProducts() {
        ArrayList<Product> testProducts= new ArrayList<>();
        Product product= new Product(1, null, 100,"PLN", null, null, null);
        testProducts.add(product);
        Supplier supplier = new Supplier("SupplierName","supplierDisc");
        supplier.setProducts(testProducts);
        assertEquals(testProducts, supplier.getProducts());
    }
    @org.junit.jupiter.api.Test
    void testAddProduct() {
        ArrayList<Product> testProducts= new ArrayList<>();
        Product product= new Product(1, null, 100,"PLN", null, null, null);
        testProducts.add(product);
        Supplier supplier = new Supplier("SupplierName","supplierDisc");
        supplier.addProduct(product);
        assertEquals(testProducts, supplier.getProducts());
    }


}