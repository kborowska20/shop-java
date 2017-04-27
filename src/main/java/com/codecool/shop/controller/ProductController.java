package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;

public class ProductController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();

    public static void showAllProducts() {
        ProductView.printProductList(productDao.getAll());
    }

    public static void findProductBy(Integer id) {
        ProductView.printProduct(productDao.find(id));
    }

    public static void getProductsBy(ProductCategory category) {
        ProductView.printProductList(productDao.getBy(category));
    }
    public static void getProductsBy(Supplier supplier) {
        ProductView.printProductList(productDao.getBy(supplier));
    }

    public static void addProduct(Product product) {
        productDao.add(product);
    }

    public static void removeProduct(Integer id) {
        productDao.remove(id);
    }

}