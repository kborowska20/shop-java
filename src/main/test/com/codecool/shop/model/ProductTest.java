package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by morthan on 22.05.17.
 */
class ProductTest {
    @Test
    void testToString() {
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.5f, "PLN",
                "TestDescription", productCategory, supplier);
        assertEquals("id: 0, name: TestProduct, defaultPrice: 1,50, defaultCurrency: PLN," +
                " productCategory: TestCategory, supplier: TestSupplier", product.toString());
    }

    @Test
    void testGetDefaultPrice() {
        Product product = new Product(null, 0, "PLN",
                null, null, null);
        product.setDefaultPrice(1.5f);
        assertEquals(1.5f, product.getDefaultPrice());
    }

    @Test
    void testGetDefaultCurrency() {
        Product product = new Product(null, 0, "PLN",
                null, null, null);
        assertEquals("PLN", product.getDefaultCurrency().toString());

    }

    @Test
    void testGetPrice() {
        Product product = new Product(null, 0, "PLN",
                null, null, null);
        product.setDefaultPrice(1.5f);
        assertEquals("1.5 PLN", product.getPrice());

    }

    @Test
    void testGetProductCategory() {
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product(null, 0, "PLN",
                null, null, null);
        product.setProductCategory(productCategory);
        assertEquals(productCategory, product.getProductCategory());
    }

    @Test
    void testGetSupplier() {
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        Product product = new Product(null, 0, "PLN",
                null, null, null);
        product.setSupplier(supplier);
        assertEquals(supplier, product.getSupplier());
    }
}