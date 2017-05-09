package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;
import jdk.nashorn.internal.ir.annotations.Ignore;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();
    private static ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();
    private static SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();


    public ModelAndView renderAllProducts(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("productList", productDao.getAll());

        return new ModelAndView(params, "product/index");
    }

    public static void findProductBy(Integer id) {
        ProductView.printProduct(productDao.find(id));
    }

    public static void renderAllProducts() {
        ProductCategoryController.renderAllCategories();
        MenuController.showMessage("Please enter ID of the category: ");
        Integer userCategoryIdInput = InputCollector.getNextInt();

        ProductView.printProductList(productDao.getBy(categoryDao.find(userCategoryIdInput)));
    }

    public static void getProductsBySupplier() {
        SupplierController.renderAllSuppliers();
        MenuController.showMessage("Please enter ID of the supplier: ");
        Integer userSupplierIdInput = InputCollector.getNextInt();

        ProductView.printProductList(productDao.getBy(supplierDao.find(userSupplierIdInput)));
    }

    @Ignore
    public static void addProduct(Product product) {
        productDao.add(product);
    }

    public static void removeProduct(Integer id) {
        productDao.remove(id);
    }

}