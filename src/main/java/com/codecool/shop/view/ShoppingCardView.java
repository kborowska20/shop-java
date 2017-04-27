package com.codecool.shop.view;

import java.util.ArrayList;
import java.util.List;
import static com.codecool.shop.view.MenuView.printStringList;

public class ShoppingCardView {
    public static void viewShoppingCardMenu() {
        List<String> shoppingCardMenu = new ArrayList<>();
        shoppingCardMenu.add("");
        shoppingCardMenu.add("----- ShoppingCart Menu -----");
        shoppingCardMenu.add("1. Show items in my Cart");
        shoppingCardMenu.add("2. Remove item ");
        shoppingCardMenu.add("3. Change quantity of item ");
        shoppingCardMenu.add("4. Checkout item");
        shoppingCardMenu.add("5. Buy (all items in Shopping Cart) ");
        shoppingCardMenu.add("6. View payment confirmation");
        shoppingCardMenu.add("7. Use Promo Code");
        shoppingCardMenu.add("0. Exit to Main Menu");
        shoppingCardMenu.add("----------------------------");
        shoppingCardMenu.add("");
        printStringList(shoppingCardMenu);
    }

}
