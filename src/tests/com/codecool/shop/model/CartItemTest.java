package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    public void TestGetTotalPrice(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.7f, "PLN",
                "TestDescription", productCategory, supplier);        CartItem cartItem = new CartItem(product,2);
        Float totalPrice = cartItem.getTotalPrice();
        Float realPrice = 3.4f;
        assertEquals(totalPrice,realPrice);
    }

    @Test
    public void TestGetProduct(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.7f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        Product productCartItem = cartItem.getProduct();
        assertEquals(product,productCartItem);
    }

    @Test
    public void TestGetId(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.5f, "PLN",
                "TestDescription", productCategory, supplier);
        Product product1 = new Product("TestProduct", 1.5f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        CartItem cartItem1 = new CartItem(product1,1);
        Integer cartItemId = cartItem.getId();
        Integer cartItemId1 = cartItem1.getId();
        assertTrue(cartItemId < cartItemId1);
    }

    @Test
    public void TestGetProductQuantity(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.7f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        Integer cartIteamQuantity = cartItem.getProductQuantity();
        Integer quantity = 2;
        assertEquals(quantity,cartIteamQuantity);
    }

    @Test
    public void TestIsAddToProductQuantityIsCounting(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.5f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        cartItem.addToProductQuantity(5);
        Integer cartIteamQuantity = cartItem.getProductQuantity();
        Integer quantity = 7;
        assertEquals(quantity,cartIteamQuantity);
    }

    @Test
    public void TestIsGetDefReturn(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product("TestProduct", 1.5f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        cartItem.setProductQuantity(5);
        Integer cartIteamQuantity = cartItem.getProductQuantity();
        Integer quantity = 5;
        assertEquals(quantity,cartIteamQuantity);
    }

    @Test
    public void TestIsToString(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product(2,"TestProduct", 1.7f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        String cartItemToString = cartItem.toString();
        String quantity = "CartItem{id=0, product=id: 2, name: TestProduct, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: TestCategory, supplier: TestSupplier, productQuantity=2, totalPrice=3.4}";
        assertEquals(quantity,cartItemToString);
    }

    @Test
    public void TestIsSetChangeQuantity(){
        Supplier supplier = new Supplier("TestSupplier", "Testscription");
        ProductCategory productCategory = new ProductCategory("TestCategory");
        Product product = new Product(2,"TestProduct", 1.7f, "PLN",
                "TestDescription", productCategory, supplier);
        CartItem cartItem = new CartItem(product,2);
        cartItem.setProductQuantity(6);
        String cartItemToString = cartItem.toString();
        String quantity = "CartItem{id=0, product=id: 2, name: TestProduct, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: TestCategory, supplier: TestSupplier, productQuantity=6, totalPrice=10.2}";
        assertEquals(quantity,cartItemToString);
    }

}