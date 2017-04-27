package com.codecool.shop.view;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
    public static void viewMainMenu() {
        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("\tView products");
        mainMenu.add("\tView products by category");
        mainMenu.add("\tView categories");
        mainMenu.add("\tView products by supplier");
        mainMenu.add("\tView suppliers");
        mainMenu.add("\tShopping cart menu");

        printMessage("------- Online Shop menu -------");
        printStringList(mainMenu);
        MenuView.printMessage("0:\tExit");
        printMessage("--------------------------------");
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    static void printStringList(List<String> stringList) {
        for (String entry : stringList) {
            printMessage((stringList.indexOf(entry) + 1) + ": " + entry);
        }
    }


}