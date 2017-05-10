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
    private static DbConnector<Supplier> dbConn = new DbConnector<>();

    @Override
    public void add(Supplier supplier) {
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            String query = "INSERT OR REPLACE INTO ";
            if (supplier != null) {
                query += "supplier (id, name, description) " +
                        "VALUES ((SELECT id FROM product WHERE id=" +
                        supplier.getId() + "), '" +
                        supplier.getName() + "', '" +
                        supplier.getDescription() + "');";
            } else {
                throw new TypeMismatchException("Unsupported type of the object provided!");
            }

            dbStatement.execute(query);
            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        Supplier foundSupplier = null;
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM supplier WHERE id=" + id);

            if (resultSet.next()) {
                foundSupplier = new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            } else {
                foundSupplier = new Supplier("None");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundSupplier;
    }

    @Override
    public void remove(int id) {
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            dbStatement.execute("DELETE FROM supplier WHERE id=" + id);

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supplierList = new ArrayList<>();
        try {
            supplierList = dbConn.getAllSuppliers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }
}
