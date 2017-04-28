package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.MenuView;

public class CheckoutController {

    public static void checkoutItems(ShoppingCart shoppingCart) {
        MenuView.printMessage("You're about to checkout items for a total of: " +
                shoppingCart.calculateCheckoutPrice());

        MenuView.printMessage("Please enter your full name:");
        InputCollector.getNext();
        MenuView.printMessage("Please enter your city:");
        InputCollector.getNext();

        shoppingCart.getItemList().clear();
        MenuView.printMessage("Transaction confirmed! Thank you for buying from our shop.");
    }
}
