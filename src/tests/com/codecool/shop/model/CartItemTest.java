package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    public void TestGetTotalPrice(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        Float totalPrice = cartItem.getTotalPrice();
        Float realPrice = 3.4f;
        assertEquals(totalPrice,realPrice);
    }
    @Test
    public void TestGetProduct(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        Product productCartItem = cartItem.getProduct();
        assertEquals(product,productCartItem);
    }
    @Test
    public void TestGetId(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        Integer cartItemId = cartItem.getId();
        Integer realId = 2;
        assertEquals(realId,cartItemId);
    }
    @Test
    public void TestGetProductQuantity(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        Integer cartIteamQuantity = cartItem.getProductQuantity();
        Integer quantity = 2;
        assertEquals(quantity,cartIteamQuantity);
    }
    @Test
    public void TestIsAddToProductQuantityIsCounting(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        cartItem.addToProductQuantity(5);
        Integer cartIteamQuantity = cartItem.getProductQuantity();
        Integer quantity = 7;
        assertEquals(quantity,cartIteamQuantity);
    }

}