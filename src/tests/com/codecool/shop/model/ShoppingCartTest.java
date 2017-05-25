package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    public void testIsMethodAddItem(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        Product product1 = productDaoSQLite.find(3);
        CartItem cartItem = new CartItem(product,2);
        CartItem cartItem1 = new CartItem(product1,3);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cartItem);
        shoppingCart.addProduct(cartItem1);
        ArrayList<CartItem> shoppingCartList = shoppingCart.getItemList();
        ArrayList<CartItem> list = new ArrayList<>();
        list.add(cartItem);
        list.add(cartItem1);
        assertEquals(list,shoppingCartList);
    }
    @Test
    public void testIsMethodRemoveItem(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        CartItem cartItem = new CartItem(product,2);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cartItem);
        boolean removeBoolen = shoppingCart.removeProduct(cartItem);
        assertEquals(true, removeBoolen);
    }
    @Test
    public void testMethodCalculateCheckoutPrice(){
        DbConnector dbConnector = new DbConnector();
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(dbConnector.getConnection());
        Product product = productDaoSQLite.find(2);
        Product product1 = productDaoSQLite.find(3);
        CartItem cartItem = new CartItem(product,2);
        CartItem cartItem1 = new CartItem(product1,3);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(cartItem);
        shoppingCart.addProduct(cartItem1);
        Float shoppingCartPrice = shoppingCart.calculateCheckoutPrice();
        Float realCheckoutPrice = 12.7f;
        boolean areAlmostEquals = Math.abs(shoppingCartPrice-realCheckoutPrice)<0.001;
        assertTrue(areAlmostEquals);
    }
}