package com.codecool.shop.controller;

import com.codecool.shop.model.CartItem;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        HashMap<String,ArrayList<CartItem>> shoppingCartModel = (HashMap<String, ArrayList<CartItem>>) shoppingCartModelAndView.getModel();
        ArrayList<CartItem> arr = shoppingCartModel.get("cartItemList");
        Integer intCat = arr.get(0).getId();
        assertEquals("{cartItemList=[CartItem{id=" + intCat + ", product=id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, productQuantity=1, totalPrice=1.7}], supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix, description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga, description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]}", shoppingCartModelAndView.getModel().toString());
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
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        HashMap<String, ArrayList<CartItem>> shoppingCartModel = (HashMap<String, ArrayList<CartItem>>) shoppingCartModelAndView.getModel();
        ArrayList<CartItem> arr = shoppingCartModel.get("cartItemList");
        Integer intCat = arr.get(0).getId();
        HashMap<String, String> hashMap1 = new HashMap<String, String>();
        hashMap1.put(":pid", intCat.toString());
        hashMap1.put("item-quantity", "5");
        when(req.params()).thenReturn(hashMap1);
        shoppingCartController.handleRemoveFromCartRequest(req,res);
        ModelAndView testModelandView = shoppingCartController.renderCartItems(req, res);
        assertEquals("{cartItemList=[], supplierList=[id: 1, name: Mlekpol, description: Polish dairy products.," +
                        " id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix," +
                        " description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga, description: Our food isn't safe to eat.," +
                        " id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, " +
                        "description: Actually, only Mom knows how to make the bread we sell.]}",
                shoppingCartModelAndView.getModel().toString());
    }

    @Test
    public void testHandleEditQuantityRequest() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        Request req1 = mock(Request.class);
        HashMap<String, String> hashMap = new HashMap<String,String>();
        hashMap.put(":pid","2");
        when(req.params()).thenReturn(hashMap);
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        ProductController productController = new ProductController(conn);
        ShoppingCartController shoppingCartController = new ShoppingCartController(conn, productController);
        shoppingCartController.handleAddToCartRequest(req,res);
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        HashMap<String,ArrayList<CartItem>> shoppingCartModel = (HashMap<String, ArrayList<CartItem>>) shoppingCartModelAndView.getModel();
        ArrayList<CartItem> arr = shoppingCartModel.get("cartItemList");
        Integer intCat = arr.get(0).getId();
        HashMap<String, String> hashMap1 = new HashMap<String,String>();
        hashMap1.put(":pid",intCat.toString());
        hashMap1.put("item-quantity","5");
        when(req1.params()).thenReturn(hashMap1);
        shoppingCartController.handleEditQuantityRequest(req1,res);
        assertEquals("{cartItemList=[CartItem{id=" + intCat + ", product=id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, productQuantity=5, totalPrice=8.5}], supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix, description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga, description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]}", shoppingCartModelAndView.getModel().toString());
    }

    @Test
    public void testHandleCheckoutRequest() throws SQLException {
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
        shoppingCartController.handleCheckoutRequest(req,res);
        ModelAndView shoppingCartModelAndView = shoppingCartController.renderCartItems(req, res);
        assertEquals("{cartItemList=[], supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix, description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga, description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples., id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]}",shoppingCartModelAndView.getModel().toString());
    }
}