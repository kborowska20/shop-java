package com.codecool.shop.view;

import com.codecool.shop.model.CartItem;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.view.MenuView.printStringList;

public class ShoppingCartView {
    public static void viewShoppingCardMenu() {
        List<String> shoppingCartMenu = new ArrayList<>();
        shoppingCartMenu.add("Show items in the cart");
        shoppingCartMenu.add("Add item to cart");
        shoppingCartMenu.add("Remove item from cart");
        shoppingCartMenu.add("Change quantity of an item");
        shoppingCartMenu.add("Checkout all items");
        shoppingCartMenu.add("Use Promo code");

        MenuView.printMessage("------ Shopping cart menu ------");
        MenuView.printStringList(shoppingCartMenu);
        MenuView.printMessage("0:\tExit to main menu");
        MenuView.printMessage("--------------------------------");
    }

    public static void printCartItemList(List<CartItem> cartItemList) {
        for (CartItem cartItem : cartItemList) {
            printCartItem(cartItem);
        }
    }

    private static void printCartItem(CartItem cartItem) {
        System.out.println(cartItem.toString());
    }
}
