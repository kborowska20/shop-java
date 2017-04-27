package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuView {
    public static void viewMainMenu(){
        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("");
        mainMenu.add("----- Online shop Menu -----");
        mainMenu.add("\tSelect an option: ");
        mainMenu.add("\t1. View products");
        mainMenu.add("\t2. View products by category");
        mainMenu.add("\t3. View categories");
        mainMenu.add("\t4. View products by supplier");
        mainMenu.add("\t5. View suppliers");
        mainMenu.add("\t6. View shopping cart");
        mainMenu.add("\t7. Shopping cart menu");
        mainMenu.add("\t0. Exit ");
        mainMenu.add("----------------------------");
        mainMenu.add("");
        printStringList(mainMenu);
    }


    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void printProductList(List<Product> productList) {
        for (Product product : productList) {
            printMessage((productList.indexOf(product) + 1) + product.toString());
        }
    }

    public static void printSupplierList(List<Supplier> supplierList) {
        for (Supplier supplier : supplierList) {
            printMessage((supplierList.indexOf(supplier)) + 1 + ": " + supplier.toString());
        }
    }

    public static void printCategoryList(List<ProductCategory> categoryList) {
        for (ProductCategory category : categoryList) {
            printMessage((categoryList.indexOf(category) + 1) + ": " + category.toString());
        }
    }

    public static void printStringList(List<String> stringList) {
        for (String entry : stringList) {
            printMessage((stringList.indexOf(entry) + 1) + ": " + entry);
        }
    }


}