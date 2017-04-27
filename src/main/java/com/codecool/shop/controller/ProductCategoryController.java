package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductCategoryView;

public class ProductCategoryController {
    public static ProductCategoryDaoSQLite categoryDao;

    public static void showAllCategories() {
        ProductCategoryView.printProductList(categoryDao.getAll());
    }

    public static void showCategoryBy(Integer id) {
        ProductCategoryView.printCategory(categoryDao.find(id));
    }

    public static void addCategory(ProductCategory category) {
        categoryDao.add(category);
    }

    public static void removeCategory(Integer id) {
        categoryDao.remove(id);
    }
}
