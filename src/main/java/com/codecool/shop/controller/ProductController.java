package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static ProductDaoSQLite productDao = new ProductDaoSQLite();
    private static ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();
    private static SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();


    public ModelAndView renderProducts(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("supplierList", supplierDao.getAll());

        if (req.params().containsKey(":cid")) {
            Integer categoryID = Integer.parseInt(req.params(":cid"));
            ProductCategory chosenCategory = categoryDao.find(categoryID);

            params.put("productList", productDao.getBy(chosenCategory));
        } else if (req.params().containsKey(":sid")) {
            Integer supplierID = Integer.parseInt(req.params(":sid"));
            Supplier supplier = supplierDao.find(supplierID);

            params.put("productList", productDao.getBy(supplier));
        } else {
            params.put("productList", productDao.getAll());
        }
        return new ModelAndView(params, "product/index");
    }

    public static void addProduct(Product product) {
        productDao.add(product);
    }

    public static void removeProduct(Integer id) {
        productDao.remove(id);
    }

}