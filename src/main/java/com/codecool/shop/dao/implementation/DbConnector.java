package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.sun.corba.se.impl.io.TypeMismatchException;

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

    void insert(T t) throws SQLException {
        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();

            String query = "INSERT OR REPLACE INTO ";
            if (t instanceof Product) {
                query += "product (id, name, defaultPrice, currencyString, description, categoryID, supplierID)"
                        + " VALUES ((SELECT id FROM product WHERE id=" +
                        ((Product) t).getId() + "), '" +
                        ((Product) t).getName() + "', " +
                        ((Product) t).getDefaultPrice() + ", 'PLN', '" +
                        ((Product) t).getDescription() + "', " +
                        ((Product) t).getProductCategory().getId() + ", " +
                        ((Product) t).getSupplier().getId() + ");";

            } else if (t instanceof ProductCategory) {
                query += "productCategory (id, name, department, description) " +
                        "VALUES ((SELECT id FROM product WHERE id=" +
                        ((ProductCategory) t).getId() + "), '" +
                        ((ProductCategory) t).getName() + "', '" +
                        ((ProductCategory) t).getDepartment() + "', '" +
                        ((ProductCategory) t).getDescription() + "');";
            } else if (t instanceof Supplier) {
                query += "supplier (id, name, description) " +
                        "VALUES ((SELECT id FROM product WHERE id=" +
                        ((Supplier) t).getId() + "), '" +
                        ((Supplier) t).getName() + "', '" +
                        ((Supplier) t).getDescription() + "');";
            } else {
                throw new TypeMismatchException("Unsupported type of the object provided!");
            }

            System.out.println(query);
            dbStatement.execute(query);
            dbStatement.close();
            this.closeConnection(conn);


        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Couldn't upsert to database!");
        }
    }

    void insert(List<T> insertList) {
        try {
            for (T insertedElement : insertList) {
                this.insert(insertedElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void delete(T t) throws SQLException {
        Integer itemId = null;
        String itemClassName = null;
        System.out.println(t.getClass().getName().toLowerCase());
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
        Supplier supplier = new Supplier(null, null);
        ProductCategory category = new ProductCategory(null, null, null);

        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM product;");

            if (t instanceof ProductCategory) {
                category = ((ProductCategory) t);
                resultSet = dbStatement.executeQuery("SELECT * FROM product WHERE categoryID=" +
                        category.getId());
            } else if (t instanceof Supplier) {
                supplier = ((Supplier) t);
                resultSet = dbStatement.executeQuery("SELECT * FROM product WHERE supplierID=" +
                        supplier.getId());
            }


            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        category, supplier));
            }

            resultSet.close();
            dbStatement.close();
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't delete from database!");

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
