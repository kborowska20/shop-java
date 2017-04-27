package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.awt.*;
import java.util.List;

public class MenuView {
    public static void viewMainMenu(){
        System.out.println("");
        System.out.println("----- Online shop Menu -----");
        System.out.println("\tSelect an option: ");
        System.out.println("\t1. View products");
        System.out.println("\t2. View categories");
        System.out.println("\t3. View suppliers");
        System.out.println("\t4. View basket ");
        System.out.println("\t0. Exit ");
        System.out.println("----------------------------");
        System.out.println("");
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


}