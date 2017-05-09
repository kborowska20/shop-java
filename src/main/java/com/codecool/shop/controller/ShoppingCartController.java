package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();
    private ShoppingCart shoppingCart = new ShoppingCart();

    public ModelAndView renderCartItems(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("cartItemList", shoppingCart.getItemList());

        return new ModelAndView(params, "basket/index");
    }

//    public static void handleAddToCartRequest(ShoppingCart shoppingCart) {
//        Product addedProduct = productDao.find(productIdInput);
//        if (addedProduct != null && req.queryParams() > 0 && req.queryParams < 250) {
//            shoppingCart.addProduct(new CartItem(addedProduct, productQuantityInput));
//        } else {
//            throw new ArrayIndexOutOfBoundsException();
//        }
//    }
//
//    public static void handleRemoveFromCartRequest(ShoppingCart shoppingCart) {
//        ShoppingCartController.renderCartItems(shoppingCart);
//        MenuController.showMessage("Please select ID of the item:");
//        Integer itemIdInput = InputCollector.getNextInt();
//
//        shoppingCart.removeProduct(getItemBy(shoppingCart, itemIdInput));
//    }
//
//    private static CartItem getItemBy(ShoppingCart shoppingCart, Integer id) {
//        CartItem foundCartItem = null;
//        for (CartItem cartItem : shoppingCart.getItemList()) {
//            if (cartItem.getId().equals(id)) {
//                foundCartItem = cartItem;
//                break;
//            }
//        }
//        return foundCartItem;
//    }
//
//    public static void handleEditQuantityRequest(ShoppingCart shoppingCart) {
//        ShoppingCartController.renderCartItems(shoppingCart);
//        MenuController.showMessage("Please select ID of the item:");
//        Integer cartItemId = InputCollector.getNextInt();
//
//        MenuController.showMessage("Please input new quantity:");
//        Integer newItemQuantity = InputCollector.getNextInt();
//
//
//        if (newItemQuantity > 0 && newItemQuantity < 250) {
//            getItemBy(shoppingCart, cartItemId).setProductQuantity(newItemQuantity);
//        } else {
//            throw new ArithmeticException("Too many items requested!");
//        }
//    }
//
//
//    public static void handleCheckoutRequest(ShoppingCart shoppingCart) {
//        MenuView.printMessage("You're about to checkout items for a total of: " +
//                shoppingCart.calculateCheckoutPrice());
//
//        MenuView.printMessage("Please enter your full name:");
//        InputCollector.getNext();
//        MenuView.printMessage("Please enter your city:");
//        InputCollector.getNext();
//
//        shoppingCart.getItemList().clear();
//        MenuView.printMessage("Transaction confirmed! Thank you for buying from our shop.");
//    }
}
