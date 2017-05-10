package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductCategoryView;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryController {
    private static ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();

    public ModelAndView renderAllCategories(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryList", categoryDao.getAll());
        return new ModelAndView(params, "category/index");
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
