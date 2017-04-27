package com.codecool.shop.view;

import com.codecool.shop.model.Product;

import java.util.List;

public class ProductView {
    public static void printProductList(List<Product> productList) {
        for (Product product : productList) {
            printProduct(product);
        }
    }

    public static void printProduct(Product product) {
        System.out.println(product.toString());
    }


}
