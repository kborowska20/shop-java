package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductCategoryView;

public class ProductCategoryController {
    private static ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();

    public static void renderAllCategories() {
        ProductCategoryView.printCategoryList(categoryDao.getAll());
    }

    public static void showCategoryBy(Integer id) {
        ProductCategoryView.printCategory(categoryDao.find(id));
    }

    public static void addCategory(String newCategoryName, String newCategoryDepartment, String newCategoryDescription) {
        categoryDao.add(new ProductCategory(newCategoryName, newCategoryDepartment, newCategoryDescription));
    }

    public static void removeCategory(Integer id) {
        categoryDao.remove(id);
    }
}
