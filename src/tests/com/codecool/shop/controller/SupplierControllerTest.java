package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by oskar on 25.05.17.
 */
class SupplierControllerTest {
    @Test
    void TestRenderAllSuppliers() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:src/tests/com/codecool/shop/resources/db/Products_test.db");
        SupplierController supplierController = new SupplierController(conn);
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ModelAndView modelAndView = supplierController.renderAllSuppliers(request, response);
        assertTrue(modelAndView.getModel().toString().contains("supplierList=[id: 1, name: Mlekpol, description: Polish dairy products"));

    }

}