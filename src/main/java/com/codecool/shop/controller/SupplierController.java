package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.SupplierView;

public class SupplierController {
    public static SupplierDaoSQLite supplierDao;

    public static void showAllSuppliers() {
        SupplierView.printSupplierList(supplierDao.getAll());
    }

    public static void showSupplierBy(Integer id) {
        SupplierView.printProduct(supplierDao.find(id));
    }

    public static void addSupplier(Supplier supplier) {
        supplierDao.add(supplier);
    }

    public static void removeSupplier(Integer id) {
        supplierDao.remove(id);
    }
}
