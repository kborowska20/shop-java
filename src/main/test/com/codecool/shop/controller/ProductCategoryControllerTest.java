package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.DbConnector;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by oskar on 22.05.17.
 */
class ProductCategoryControllerTest {
    @Test
    void testRenderAllCategories() {
        Connection conn = new DbConnector().getConnection();
        ProductCategoryController productCategoryController = new ProductCategoryController(conn);
        ModelAndView modelAndView = productCategoryController.renderAllCategories(null, null);

    }

    @Test
    void testAddCategory() {
    }

    @Test
    void testRemoveCategory() {
    }

}