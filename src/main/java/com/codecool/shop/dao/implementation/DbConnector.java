package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DbConnector<T> {

    Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/Products.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    void delete(T t) throws SQLException {
        Integer itemId = null;
        String itemClassName = null;
        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();

            if (t instanceof Product) {
                itemId = ((Product) t).getId();
                itemClassName = "product";
            } else if (t instanceof ProductCategory) {
                itemId = ((ProductCategory) t).getId();
                itemClassName = "productCategory";
            } else if (t instanceof Supplier) {
                itemId = ((Supplier) t).getId();
                itemClassName = "supplier";
            }

            String query = "DELETE FROM " + itemClassName + " WHERE id=" + itemId;

            dbStatement.execute(query);
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't delete from database!");
        }
    }

    List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            productList = filterProductsBy(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    List<Product> filterProductsBy(T t) throws SQLException {
        List<Product> productList = new ArrayList<>();
        ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();
        SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();

        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();

            String query = "SELECT * FROM PRODUCT ";

            if (t instanceof ProductCategory) {
                query += "WHERE product.categoryID=" + ((ProductCategory) t).getId();
            } else if (t instanceof Supplier) {
                query += " WHERE product.supplierID=" + ((Supplier) t).getId();
            }

            ResultSet resultSet = dbStatement.executeQuery(query);

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        (t instanceof ProductCategory) ? ((ProductCategory) t) : categoryDao.find(resultSet.getInt(6)),
                        (t instanceof Supplier) ? ((Supplier) t) : supplierDao.find(resultSet.getInt(7))));
            }

            resultSet.close();
            dbStatement.close();
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't fetch from database!");

        }
        return productList;
    }

    List<ProductCategory> getAllCategories() throws SQLException {
        List<ProductCategory> categoryList = new ArrayList<>();

        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();
            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM productCategory;");

            while (resultSet.next()) {
                categoryList.add(new ProductCategory(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")));
            }

            resultSet.close();
            dbStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't get categories from database!");

        }
        return categoryList;

    }

    List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();

        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();
            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM supplier;");

            while (resultSet.next()) {
                supplierList.add(new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't get suppliers from database!");

        }
        return supplierList;
    }

}
