package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ProductController extends BaseController {

    public ProductController(Connection conn) {
        super(conn);
    }

    public ModelAndView renderProducts(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("supplierList", getSupplierDao().getAll());

        if (req.params().containsKey(":cid")) {
            Integer categoryID = Integer.parseInt(req.params(":cid"));
            ProductCategory chosenCategory = getCategoryDao().find(categoryID);

            params.put("productList", getProductDao().getBy(chosenCategory));
        } else if (req.params().containsKey(":sid")) {
            Integer supplierID = Integer.parseInt(req.params(":sid"));
            Supplier supplier = getSupplierDao().find(supplierID);

            params.put("productList", getProductDao().getBy(supplier));
        } else {
            params.put("productList", getProductDao().getAll());
        }
        return new ModelAndView(params, "product/index");
    }

    public void addProduct(Product product) {
        getProductDao().add(product);
    }

    public void removeProduct(Integer id) {
        getProductDao().remove(id);
    }

}