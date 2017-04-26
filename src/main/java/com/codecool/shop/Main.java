package com.codecool.shop;

import com.codecool.shop.controller.InputCollector;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.MenuView;

import com.codecool.shop.model.Supplier;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) {

        Integer option = -1;

            while (option != 0) {
                try {
                    switch (option) {
                        case 1:

                            //                                ProductDaoSQLite dbConn = new ProductDaoSQLite();
                            //            for (Product product : dbConn.getAll()) {
                            //                System.out.println(product.toString());
                            //            }
                            //
                            //            dbConn.add(new Product("Name", 3f,
                            //                    "PLN", "Desc",
                            //                    new ProductCategory("Test", "Test", "Test"),
                            //                    new Supplier("Test", "Test")));
                            System.out.println("Products");
                            MenuView.viewMainMenu();
                            option = InputCollector.getNextInt();
                        case 2:
                            System.out.println("Categories");
                            MenuView.viewMainMenu();
                            option = InputCollector.getNextInt();
                        case 3:
                            System.out.println("Suppliers");
                            MenuView.viewMainMenu();
                            option = InputCollector.getNextInt();
                        case 4:
                            System.out.println("Basket");
                            MenuView.viewMainMenu();
                            option = InputCollector.getNextInt();
                        case 5:
                            System.out.println("See You next time !");
                            System.exit(0);
                        default:
                            MenuView.viewMainMenu();
                            option = InputCollector.getNextInt();
                    }
                    } catch(InputMismatchException e){
                        System.err.println("Not a valid input. Error :");
                    }
            }
        }
    }


