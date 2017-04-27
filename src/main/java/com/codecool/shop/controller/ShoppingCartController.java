package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.ShoppingCartView;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class ShoppingCartController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();

    public static void showCartItems(ShoppingCart shoppingCart) {
        ShoppingCartView.printCartItemList(shoppingCart.getItemList());
    }

    public static void addToCart(ShoppingCart shoppingCart, Integer productId, Integer quantity) {
        Product addedProduct = productDao.find(productId);
        if (addedProduct != null && quantity > 0 && quantity < 250) {
            shoppingCart.addProduct(new CartItem(addedProduct, quantity));
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void removeFromCart(ShoppingCart shoppingCart, Integer cartItemId) {
        shoppingCart.removeProduct(shoppingCart.getItemList().get(cartItemId));
    }

    public static void editQuantity(ShoppingCart shoppingCart, Integer cartItemId, Integer quantity) {
        if (quantity > 0 && quantity < 250) {
            shoppingCart.getItemList().get(cartItemId).setProductQuantity(quantity);
        } else {
            throw new ArithmeticException("Too many items requested!");
        }
    }
}
