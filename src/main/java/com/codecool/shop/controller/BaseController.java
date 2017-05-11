package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.dao.implementation.SupplierDaoSQLite;

import java.sql.Connection;

abstract class BaseController {
    private Connection conn;
    private ProductDao productDao;
    private ProductCategoryDao categoryDao;
    private SupplierDao supplierDao;

    BaseController(Connection conn) {
        this.conn = conn;
        this.productDao = new ProductDaoSQLite(conn);
        this.categoryDao = new ProductCategoryDaoSQLite(conn);
        this.supplierDao = new SupplierDaoSQLite(conn);
    }

    public Connection getConn() {
        return conn;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getCategoryDao() {
        return categoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }
}
