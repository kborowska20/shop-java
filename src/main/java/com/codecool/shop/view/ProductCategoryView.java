package com.codecool.shop.view;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryView {
    public static void printCategoryList(List<ProductCategory> categoryList) {
        for (ProductCategory category : categoryList) {
            printCategory(category);
        }
    }

    public static void printCategory(ProductCategory category) {
        System.out.println(category.toString());
    }
}
