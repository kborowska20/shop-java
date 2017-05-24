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
        params.put("supplierList", this.getSupplierDao().getAll());
        params.put("categoryList", this.getCategoryDao().getAll());

        if (req.params().containsKey(":cid")) {
            Integer categoryID = Integer.parseInt(req.params().get(":cid"));
            ProductCategory chosenCategory = this.getCategoryDao().find(categoryID);

            params.put("productList", this.getProductDao().getBy(chosenCategory));
        } else if (req.params().containsKey(":sid")) {
            Integer supplierID = Integer.parseInt(req.params().get(":sid"));
            Supplier supplier = this.getSupplierDao().find(supplierID);

            params.put("productList", this.getProductDao().getBy(supplier));
        } else {
            params.put("productList", this.getProductDao().getAll());
        }
        return new ModelAndView(params, "product/index");
    }

    public void addProduct(Product product) {
        this.getProductDao().add(product);
    }

    public void removeProduct(Integer id) {
        this.getProductDao().remove(id);
    }

}