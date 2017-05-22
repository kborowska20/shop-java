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
        Product product = new Product("TestProduct", 1.5f, "PLN", "TestDescription", productCategory, supplier);
        assertEquals("id: 0, name: TestProduct, defaultPrice: 1,50, defaultCurrency: PLN, productCategory: TestCategory, supplier: TestSupplier", product.toString());
    }

}