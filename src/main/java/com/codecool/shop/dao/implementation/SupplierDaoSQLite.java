package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSQLite implements SupplierDao {

    private Connection conn;

    public SupplierDaoSQLite(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            Statement dbStatement = conn.createStatement();

            String query = "INSERT OR REPLACE INTO ";
            if (supplier != null) {
                query += "supplier (id, name, description) " +
                        "VALUES ((SELECT id FROM product WHERE id=" +
                        supplier.getId() + "), '" +
                        supplier.getName() + "', '" +
                        supplier.getDescription() + "');";
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
    public Supplier find(int id) {
        Supplier foundSupplier = null;
        try {
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM supplier WHERE id=" + id);

            if (resultSet.next()) {
                foundSupplier = new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            } else {
                foundSupplier = new Supplier("None");
            }

            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundSupplier;
    }

    @Override
    public void remove(int id) {
        try {
            Statement dbStatement = conn.createStatement();

            String query = "DELETE FROM supplier WHERE id=" + id;

            dbStatement.execute(query);
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supplierList = new ArrayList<>();

        try {
            Statement dbStatement = conn.createStatement();
            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM supplier;");

            while (resultSet.next()) {
                supplierList.add(new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }

            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }
}
