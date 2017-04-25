package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.*;
import java.text.MessageFormat;
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

    public void insert(T t) throws SQLException {
        try {
            Connection conn = this.connect();
            Statement dbStatement = conn.createStatement();


            String query = "INSERT OR REPLACE INTO ";
            if (t instanceof Product) {
                query += MessageFormat.format("product (id, name, defaultPrice, currencyString, description, " +
                                "categoryID, supplierID) VALUES ((SELECT id FROM product WHERE " +
                                "id={1}), {2}, {3}, {4}, {5}, {6}, {7});",
                        ((Product) t).getId(), ((Product) t).getName(), ((Product) t).getDefaultPrice(),
                        ((Product) t).getDescription(), ((Product) t).getProductCategory().getId(),
                        ((Product) t).getSupplier().getId());
            } else if (t instanceof ProductCategory) {
                query += MessageFormat.format("productCategory (id, name, department, description) " +
                                "VALUES ((SELECT id FROM product WHERE id={1}), {2}, {3}, {4});",
                        ((ProductCategory) t).getId(), ((ProductCategory) t).getName(),
                        ((ProductCategory) t).getDepartment(), ((ProductCategory) t).getDescription());
            } else if (t instanceof Supplier) {
                query += MessageFormat.format("supplier (id, name, description) " +
                                "VALUES ((SELECT id FROM product WHERE id={1}), {2}, {3});",
                        ((Supplier) t).getId(), ((Supplier) t).getName(), ((Supplier) t).getDescription());
            } else {
                throw new TypeMismatchException("Unsupported type of the object provided!");
            }

            System.out.println(query);
            dbStatement.execute(query);
            dbStatement.close();
            conn.commit();
            this.closeConnection(conn);

        } catch (Exception e) {
            throw new SQLException("Couldn't upsert to database!");
        }
    }

    public void insert(List<T> insertList) {

    }

}
