package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSQLite implements ProductCategoryDao {
    private static DbConnector<ProductCategory> dbConn = new DbConnector<>();

    @Override
    public void add(ProductCategory category) {
        try {
            dbConn.insert(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory foundCategory = null;
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM productCategory WHERE id=" + id);

            if (resultSet.next()) {
                foundCategory = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundCategory;
    }

    @Override
    public void remove(int id) {
        try {
            dbConn.delete(find(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categoryList = new ArrayList<>();
        try {
            categoryList = dbConn.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
