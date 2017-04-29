package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.ShoppingCartView;

public class ShoppingCartController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();

    public static void showCartItems(ShoppingCart shoppingCart) {
        ShoppingCartView.printCartItemList(shoppingCart.getItemList());
    }

    public static void addToCart(ShoppingCart shoppingCart) {
        ProductController.showAllProducts();
        MenuController.showMessage("Please select ID of the product:");
        Integer productIdInput = InputCollector.getNextInt();

        MenuController.showMessage("Please input quantity of the product (between 1 and 250)");
        Integer productQuantityInput = InputCollector.getNextInt();

        Product addedProduct = productDao.find(productIdInput);
        if (addedProduct != null && productQuantityInput > 0 && productQuantityInput < 250) {
            shoppingCart.addProduct(new CartItem(addedProduct, productQuantityInput));
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void removeFromCart(ShoppingCart shoppingCart) {
        ShoppingCartController.showCartItems(shoppingCart);
        MenuController.showMessage("Please select ID of the item:");
        Integer itemIdInput = InputCollector.getNextInt();

        shoppingCart.removeProduct(getItemBy(shoppingCart, itemIdInput));
    }

    private static CartItem getItemBy(ShoppingCart shoppingCart, Integer id) {
        CartItem foundCartItem = null;
        for (CartItem cartItem : shoppingCart.getItemList()) {
            if (cartItem.getId().equals(id)) {
                foundCartItem = cartItem;
                break;
            }
        }
        return foundCartItem;
    }

    public static void editQuantity(ShoppingCart shoppingCart) {
        ShoppingCartController.showCartItems(shoppingCart);
        MenuController.showMessage("Please select ID of the item:");
        Integer cartItemId = InputCollector.getNextInt();

        MenuController.showMessage("Please input new quantity:");
        Integer newItemQuantity = InputCollector.getNextInt();


        if (newItemQuantity > 0 && newItemQuantity < 250) {
            getItemBy(shoppingCart, cartItemId).setProductQuantity(newItemQuantity);
        } else {
            throw new ArithmeticException("Too many items requested!");
        }
    }
}
