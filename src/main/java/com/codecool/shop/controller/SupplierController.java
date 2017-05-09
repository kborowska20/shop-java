package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.SupplierView;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class SupplierController {
    private static SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();

    public ModelAndView renderAllSuppliers(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("supplierList", supplierDao.getAll());
        return new ModelAndView(params, "supplier/index");
    }

    public static void showSupplierBy(Integer id) {
        SupplierView.printSupplier(supplierDao.find(id));
    }

    public static void addSupplier(String newSupplierName, String newSupplierDescription) {
        supplierDao.add(new Supplier(newSupplierName, newSupplierDescription));
    }

    public static void removeSupplier(Integer id) {
        supplierDao.remove(id);
    }
}
