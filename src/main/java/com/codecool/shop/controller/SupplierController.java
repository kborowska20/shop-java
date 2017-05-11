package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class SupplierController extends BaseController {

    public SupplierController(Connection conn) {
        super(conn);
    }

    public ModelAndView renderAllSuppliers(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("supplierList", getSupplierDao().getAll());
        return new ModelAndView(params, "supplier/index");
    }
}
