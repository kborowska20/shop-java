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
            System.out.println("Connection opened succesfully.");
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

    List<Product> filterProductsBy(T t) throws TypeMismatchException {
        List<Product> productList = new ArrayList<>();
        Supplier supplier = new Supplier("", "");
        ProductCategory category = new ProductCategory("", "", "");

        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();
            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM product;");

            if (t instanceof ProductCategory) {
                category = ((ProductCategory) t);
            } else if (t instanceof Supplier) {
                supplier = ((Supplier) t);
            } else {
                throw new TypeMismatchException();
            }

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        category, supplier));
            }

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
