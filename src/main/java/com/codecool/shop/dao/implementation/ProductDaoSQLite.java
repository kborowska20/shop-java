package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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
    private Connection conn;

    private ProductCategoryDao categoryDao;
    private SupplierDao supplierDao;


    public ProductDaoSQLite(Connection conn) {
        this.conn = conn;
        categoryDao = new ProductCategoryDaoSQLite(conn);
        supplierDao = new SupplierDaoSQLite(conn);
    }

    @Override
    public void add(Product product) {
        try {
            Statement dbStatement = this.conn.createStatement();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        Product foundProduct = null;
        try {
            Statement dbStatement = this.conn.createStatement();

            ResultSet resultSet = dbStatement.executeQuery("SELECT * FROM product WHERE id=" + id);

            if (resultSet.next()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
    }

    @Override
    public void remove(int id) {
        try {
            Statement dbStatement = conn.createStatement();

            String query = "DELETE FROM product WHERE id=" + id;

            dbStatement.execute(query);
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = filterProductsBy(supplier);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(ProductCategory category) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = filterProductsBy(category);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getAll() {
        return filterProductsBy();
    }

    private List<Product> filterProductsBy() {
        try {
            return filterProductsBy(null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Product> filterProductsBy(Object object) throws SQLException {
        List<Product> productList = new ArrayList<>();

        try {
            Statement dbStatement = conn.createStatement();

            String query = "SELECT * FROM PRODUCT ";

            if (object instanceof ProductCategory) {
                query += "WHERE product.categoryID=" + ((ProductCategory) object).getId();
            } else if (object instanceof Supplier) {
                query += " WHERE product.supplierID=" + ((Supplier) object).getId();
            }

            ResultSet resultSet = dbStatement.executeQuery(query);

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getFloat("defaultPrice"),
                                resultSet.getString("currencyString"),
                                resultSet.getString("description"),
                                (object instanceof ProductCategory) ? ((ProductCategory) object) : categoryDao.find(resultSet.getInt(6)),
                                (object instanceof Supplier) ? ((Supplier) object) : supplierDao.find(resultSet.getInt(7)),
                                resultSet.getString("link")

                        )

                );
            }

            resultSet.close();
            dbStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Couldn't fetch from database!");

        }
        return productList;
    }
}
