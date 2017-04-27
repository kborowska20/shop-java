package com.codecool.shop;

import com.codecool.shop.controller.InputCollector;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.view.MenuView;

import java.util.InputMismatchException;

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
                        // So that I can browse Products within that Category
                        ProductController.showAllProducts();
                        continue;
                    case 2:
                        // So that I can browse Products within any Category
                        ProductCategoryController.showAllCategories();
                        Integer userCategoryInput = InputCollector.getNextInt();
                        // use userCategoryInput to show product within the selected category
                        continue;
                    case 3:
                        // View categories
                        ProductCategoryController.showAllCategories();
                        continue;
                    case 4:
                        // So that I can browse Products by Suppliers
                        SupplierController.showAllSuppliers();
                        Integer userSupplierInput = InputCollector.getNextInt();
                        // use userSupplierInput to show product within the selected category
                        continue;
                    case 5:
                        // View supplier
                        SupplierController.showAllSuppliers();
                        continue;
                    case 6:
                        // View shopping cart
                        continue;
                    case 7:
                        // Shopping cart menu
                        continue;
                    case 0:
                        isMenuLoopActive = false;

                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
    }
}


