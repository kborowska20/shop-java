package com.codecool.shop.controller;
import static org.mockito.Mockito.*;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.DbConnector;
import com.codecool.shop.dao.implementation.ProductCategoryDaoSQLite;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import spark.ModelAndView;
import spark.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by oskar on 22.05.17.
 */
class ProductCategoryControllerTest {


    @BeforeEach
    void clearDb() throws SQLException
    {
        HelpTestClass helpTestClass = new HelpTestClass();
        helpTestClass.clearAllDb("productCategory");
    }

    @Test
    void testRenderAllCategories() throws SQLException {
        HelpTestClass helpTestClass = new HelpTestClass();
        helpTestClass.fillDB("productCategory");
        Connection conn = null;
                conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/testDB.db");
        ProductCategoryController productCategoryController = new ProductCategoryController(conn);
        Request request = mock(Request.class);
        ModelAndView modelAndView = productCategoryController.renderAllCategories(request, null);
        assertTrue(modelAndView.getModel().toString().contains("{categoryList=[id: 1, name: Fruit, department: Food, description: " +
                "The best the Earth has given us., id: 2, name: Dairy, department: Food, description: Stolen from under a cow's nose., " +
                "id: 3, name: Pastries, department: Food, description: Tasty and fresh., id: 4, name: Meat, department: Food, description:" +
                " Always fresh., id: 5, name: Processed, department: Food, description: Forever fresh.],"
        ));
    }


    @Test
    void testAddCategory() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/testDB.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductCategoryController productCategoryController = new ProductCategoryController(conn);
        productCategoryController.addCategory("cat0","cat1","cat2");
        assertTrue(productCategoryController.renderAllCategories(null, null).getModel().toString().contains("name: cat0, department: cat1, description: cat2"));
    }


    @Test
    void testRemoveCategory() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/testDB.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductCategoryController productCategoryController = new ProductCategoryController(conn);
        productCategoryController.addCategory("cat0","cat1","cat2");

        ProductCategoryDaoSQLite productCategoryDaoSQLite = new ProductCategoryDaoSQLite(conn);
        List<ProductCategory> productCategoryList = productCategoryDaoSQLite.getAll();
        ProductCategory productCategory = productCategoryList.get(productCategoryList.size() -1);

               productCategoryController.removeCategory(productCategory.getId());
        assertTrue(productCategoryController.renderAllCategories(null, null).getModel().toString().contains("categoryList=[]"));
    }

    @AfterAll
    static void fillDb() throws SQLException{
        HelpTestClass helpTestClass = new HelpTestClass();
        helpTestClass.fillDB("productCategory");
    }
}
