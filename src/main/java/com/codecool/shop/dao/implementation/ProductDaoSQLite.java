package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ProductDaoSQLite implements ProductDao {

    @Override
    public void add(Product product) {
        DbConnector<Product> dbConn = new DbConnector<>();

        try {
            dbConn.insert(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        Product foundProduct = null;
        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM product WHERE id=" + id);

            if (resultSet.next()) {
                ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();
                SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();

                foundProduct = new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        categoryDao.find(resultSet.getInt("categoryID")),
                        supplierDao.find(resultSet.getInt("supplierID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try {
            DbConnector<Product> dbConn = new DbConnector<>();
            return dbConn.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        DbConnector<Supplier> dbConn = new DbConnector<>();
        return dbConn.filterProductsBy(supplier);
    }

    @Override
    public List<Product> getBy(ProductCategory category) {
        DbConnector<ProductCategory> dbConn = new DbConnector<>();
        return dbConn.filterProductsBy(category);
    }
}
