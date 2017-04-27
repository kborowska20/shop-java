package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;

public class ProductController {
    public static ProductDaoSQLite productDao;

    public static void showAllProducts() {
        ProductView.printProductList(productDao.getAll());
    }

    public static void showProductBy(Integer id) {
        ProductView.printProduct(productDao.find(id));
    }

    public static void addProduct(Product product) {
        productDao.add(product);
    }

    public static void removeProduct(Integer id) {
        productDao.remove(id);
    }

}