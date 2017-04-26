package com.codecool.shop;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.MenuView;


public class Main {
    public static void main(String args[]) {
        ProductDaoSQLite dbConn = new ProductDaoSQLite();
        for (Product product : dbConn.getAll()) {
            System.out.println(product.toString());
        }

        dbConn.add(new Product("Name", 3f,
                "PLN", "Desc",
                new ProductCategory("Test", "Test", "Test"),
                new Supplier("Test", "Test")));
        MenuView.viewMainMenu();
    }
}
