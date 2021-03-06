package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSQLite implements ProductCategoryDao {

    private Connection conn;

    public ProductCategoryDaoSQLite(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(ProductCategory category) {
        try {
            Statement dbStatement = conn.createStatement();
            String query = "INSERT OR REPLACE INTO ";

            if (category != null) {
                query += "productCategory (id, name, department, description) " +
                        "VALUES ((SELECT id FROM product WHERE id=" +
                        category.getId() + "), '" +
                        category.getName() + "', '" +
                        category.getDepartment() + "', '" +
                        category.getDescription() + "');";
                dbStatement.execute(query);
            } else {
                throw new TypeMismatchException("Unsupported type of the object provided!");
            }

            dbStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory foundCategory = null;
        try {
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM productCategory WHERE id=" + id);

            if (resultSet.next()) {
                foundCategory = new ProductCategory(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
            } else {
                foundCategory = new ProductCategory("None");
            }

            resultSet.close();
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundCategory;
    }

    @Override
    public void remove(int id) {
        try {
            Statement dbStatement = conn.createStatement();

            String query = "DELETE FROM productCategory WHERE id=" + id;

            dbStatement.execute(query);
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categoryList = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;

    }
}
