package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {
    @org.junit.jupiter.api.Test
    void testGetAndSetDepartment() {
        ProductCategory productCategory = new ProductCategory(null,null,null);
        productCategory.setDepartment("x");
        assertEquals("x", productCategory.getDepartment(), "getDepartment" );
    }

    @org.junit.jupiter.api.Test
    void testGetAndSetProducts() {
        ProductCategory productCategory = new ProductCategory(null,null,null);
        Product product= new Product(1, null, 100,"PLN", null, null, null);
        Product product2= new Product(1, null, 100,"PLN", null, null, null);
        ArrayList<Product> list = new ArrayList<>();
        list.add(product);
        list.add(product2);
        productCategory.setProducts(list);
        assertEquals(list, productCategory.getProducts(), "setProducts");


    }

    @org.junit.jupiter.api.Test
    void testAddProduct() {
        ProductCategory productCategory = new ProductCategory("x","X","X");
        Supplier supplier = new Supplier("x", "x");
        Product product= new Product(1, "x", 100,"PLN", "x", productCategory, supplier);
        productCategory.addProduct(product);
        assertEquals(product, productCategory.getProducts().get(0), "testAddProduct");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        ProductCategory productCategory = new ProductCategory("x","y","z");
        assertEquals("id: 0, name: x, department: y, description: z", productCategory.toString(), "testToString");
    }
    @org.junit.jupiter.api.Test
    void testInit() {
        ProductCategory productCategory = new ProductCategory("x","y","z");
        assertEquals("id: 0, name: x, department: y, description: z", productCategory.toString(), "testToString");
    }
    @org.junit.jupiter.api.Test
    void testInitializerNameOnly(){
        ProductCategory productCategory = new ProductCategory("name");
        assertEquals("name", productCategory.getName(), "testInitializer");
    }
    @org.junit.jupiter.api.Test
    void testInitializer(){
        ProductCategory productCategory = new ProductCategory(1, "name", "department", "description");
        ArrayList<Product> lista = new ArrayList<>();
        assertEquals(lista , productCategory.getProducts(), "testInitializer");
    }
}