package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.ShoppingCartView;

public class ShoppingCartController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();

    public static void showCartItems(ShoppingCart shoppingCart) {
        ShoppingCartView.printCartItemList(shoppingCart.getItemList());
    }

    public static void addToCart(ShoppingCart shoppingCart, Integer productId, Integer quantity) {
        shoppingCart.addProduct(new CartItem(productDao.find(productId), quantity));
    }

    public static void removeFromCart(ShoppingCart shoppingCart, Integer cartItemId) {
        shoppingCart.removeProduct(shoppingCart.getItemList().get(cartItemId));
    }

    public static void editQuantity(ShoppingCart shoppingCart, Integer cartItemId, Integer quantity) {
        shoppingCart.getItemList().get(cartItemId).setProductQuantity(quantity);
    }

    public static void checkoutItemsFromCart() {
        System.out.println("BURN THE COAL, PAY THE TOLL");
    }
}
