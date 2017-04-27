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

        Boolean isMenuLoopActive = true;
        while (isMenuLoopActive) {
            MenuView.viewMainMenu();
            Integer option = InputCollector.getNextInt();
            try {
                switch (option) {
                    default:
                        continue;
                    case 1:
                        //Products
//                        MenuView.printProductList();
                        break;
                    case 2:
                        //Categories
                        break;
                    case 3:
                        //Suppliers
                        break;
                    case 4:
                        //Basket
                        break;
                    case 0:
                        isMenuLoopActive = false;

                }
            } catch (InputMismatchException e) {
                System.err.println("Not a valid input. Error :");
            }
        }
    }
}


