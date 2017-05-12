
package com.codecool.shop;

import com.codecool.shop.controller.*;
import com.codecool.shop.dao.implementation.DbConnector;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;


class ShopApp {

    private ShopApp shopApp;
    private Connection conn;

    ShopApp() {
        port(8888);
        staticFileLocation("/public");

        conn = new DbConnector().getConnection();
        this.shopApp = this;

        ProductCategoryController categoryController = new ProductCategoryController(conn);
        SupplierController supplierController = new SupplierController(conn);
        ProductController productController = new ProductController(conn);
        ShoppingCartController cartController = new ShoppingCartController(conn, productController);

        ThymeleafTemplateEngine thymeleafEngine = new ThymeleafTemplateEngine();

        get("/basket/:pid/add", cartController::handleAddToCartRequest, thymeleafEngine);

        get("/basket/:pid/remove", cartController::handleRemoveFromCartRequest, thymeleafEngine);

        get("/basket/:pid/edit", cartController::handleEditQuantityRequest, thymeleafEngine);

        get("/basket/checkout", cartController::handleCheckoutRequest, thymeleafEngine);

        get("/category/:cid", productController::renderProducts, thymeleafEngine);

        get("/supplier/:sid", productController::renderProducts, thymeleafEngine);

        get("/categories", categoryController::renderAllCategories, thymeleafEngine);

        get("/suppliers", supplierController::renderAllSuppliers, thymeleafEngine);

        get("/basket", cartController::renderCartItems, thymeleafEngine);

        get("/", productController::renderProducts, thymeleafEngine);


    }

    ShopApp getShopApp() {
        return this.shopApp;
    }

    void closeConnection() throws SQLException {
        this.conn.close();
    }
}