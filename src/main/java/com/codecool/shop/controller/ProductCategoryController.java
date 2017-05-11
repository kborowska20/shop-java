package com.codecool.shop.controller;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductCategoryView;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ProductCategoryController extends BaseController {

    public ProductCategoryController(Connection conn) {
        super(conn);
    }

    public ModelAndView renderAllCategories(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("supplierList", getSupplierDao().getAll());
        params.put("categoryList", getCategoryDao().getAll());
        return new ModelAndView(params, "category/index");
    }

    public void showCategoryBy(Integer id) {
        ProductCategoryView.printCategory(getCategoryDao().find(id));
    }

    public void addCategory(String newCategoryName, String newCategoryDepartment, String newCategoryDescription) {
        getCategoryDao().add(new ProductCategory(newCategoryName, newCategoryDepartment, newCategoryDescription));
    }

    public void removeCategory(Integer id) {
        getCategoryDao().remove(id);
    }
}
