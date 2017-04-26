package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductCategoryDaoSQLite implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory foundCategory = null;
        try {
            DbConnector dbConn = new DbConnector();
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
    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
