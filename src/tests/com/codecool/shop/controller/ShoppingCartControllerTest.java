package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartControllerTest {

    @Test
    public void testAddCartItemsMethod() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        HashMap<String, String> hashMap = new HashMap<String,String>();
        hashMap.put(":pid","2");
        when(req.params()).thenReturn(hashMap);
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductController productController = new ProductController(conn);
        ShoppingCartController shoppingCartController = new ShoppingCartController(conn, productController);
        shoppingCartController.handleAddToCartRequest(req,res);
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        assertEquals("{cartItemList=[CartItem" +
                "{id=0, product=id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, productQuantity=1, totalPrice=1.7}], " +
                "supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., " +
                "id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix, description: Suck on that peanut, won't ya'?, " +
                "id: 4, name: Boongaboonga, description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]}", shoppingCartModelAndView.getModel().toString());
    }
    @Test
    public void testRemoveFromCartRequest() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        HashMap<String, String> hashMap = new HashMap<String,String>();
        hashMap.put(":pid","2");
        when(req.params()).thenReturn(hashMap);
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductController productController = new ProductController(conn);
        ShoppingCartController shoppingCartController = new ShoppingCartController(conn, productController);
        shoppingCartController.handleAddToCartRequest(req,res);
        shoppingCartController.handleRemoveFromCartRequest(req,res);
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        assertEquals("",shoppingCartModelAndView.getModel().toString());
    }
    @Test
    public void testHandleEditQuantityRequest() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        HashMap<String, String> hashMap = new HashMap<String,String>();
        hashMap.put(":pid","2");
        hashMap.put("item-quantity","5");
        when(req.params()).thenReturn(hashMap);
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductController productController = new ProductController(conn);
        ShoppingCartController shoppingCartController = new ShoppingCartController(conn, productController);
        shoppingCartController.handleAddToCartRequest(req,res);
        shoppingCartController.handleEditQuantityRequest(req,res);
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        assertEquals("{cartItemList=" +
                "[CartItem{id=2, product=id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, productQuantity=5, totalPrice=8.5}]," +
                " supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix, description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga, description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]}",shoppingCartModelAndView.getModel().toString());
    }
}