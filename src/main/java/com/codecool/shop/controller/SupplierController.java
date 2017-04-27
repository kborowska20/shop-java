package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.SupplierView;

public class SupplierController {
    private static SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();

    public static void showAllSuppliers() {
        SupplierView.printSupplierList(supplierDao.getAll());
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
