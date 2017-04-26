package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SupplierDaoSQLite implements SupplierDao {

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        Supplier foundSupplier = null;
        try {
            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM supplier WHERE id=" + id);

            if (resultSet.next()) {
                foundSupplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundSupplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
