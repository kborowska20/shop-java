package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSQLite implements ProductDao {
    private static DbConnector<Product> dbConn = new DbConnector<>();

    @Override
    public void add(Product product) {
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            String query = "INSERT OR REPLACE INTO ";
            if (product != null) {
                query += "product (id, name, defaultPrice, currencyString, description, categoryID, supplierID)"
                        + " VALUES ((SELECT id FROM product WHERE id=" +
                        product.getId() + "), '" +
                        product.getName() + "', " +
                        product.getDefaultPrice() + ", 'PLN', '" +
                        product.getDescription() + "', " +
                        product.getProductCategory().getId() + ", " +
                        product.getSupplier().getId() + ");";
                dbStatement.execute(query);
            } else {
                throw new TypeMismatchException("Unsupported type of the object provided!");
            }

            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        Product foundProduct = null;
        try {
            Connection conn = dbConn.connect();
            Statement dbStatement = conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM product WHERE id=" + id);

            if (resultSet.next()) {
                ProductCategoryDaoSQLite categoryDao = new ProductCategoryDaoSQLite();
                SupplierDaoSQLite supplierDao = new SupplierDaoSQLite();

                foundProduct = new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        categoryDao.find(resultSet.getInt("categoryID")),
                        supplierDao.find(resultSet.getInt("supplierID")));
                        resultSet.getString("link");
            }

            resultSet.close();
            dbStatement.close();
            dbConn.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
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
    public List<Product> getAll() {
        List<Product> productList = null;
        try {
            productList = dbConn.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productList = new ArrayList<>();
        try {
            DbConnector<Supplier> dbConn = new DbConnector<>();
            productList = dbConn.filterProductsBy(supplier);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(ProductCategory category) {
        List<Product> productList = new ArrayList<>();
        try {
            DbConnector<ProductCategory> dbConn = new DbConnector<>();
            productList = dbConn.filterProductsBy(category);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
