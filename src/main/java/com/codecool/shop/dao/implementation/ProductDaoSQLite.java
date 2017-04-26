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
        List<Product> productList = new ArrayList<>();
        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            // TODO
            ResultSet resultSet = dbStatement.executeQuery("SELECT name, defaultPrice, " +
                    "currencyString, description FROM product;");

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        // TODO
                        new ProductCategory("Test", "Test", "Test"),
                        new Supplier("Test", "Test")));
            }

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productList = new ArrayList<>();
        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();
            // TODO
            ResultSet resultSet = dbStatement.executeQuery("SELECT name, defaultPrice, " +
                    "currencyString, description FROM product;");

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        new ProductCategory("Test", "Test", "Test"),
                        supplier));
            }

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> productList = new ArrayList<>();
        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();
            // TODO
            ResultSet resultSet = dbStatement.executeQuery("SELECT name, defaultPrice, " +
                    "currencyString, description FROM product;");

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        productCategory,
                        new Supplier("Test", "Test")));
            }

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
