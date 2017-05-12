
package com.codecool.shop;

import com.codecool.shop.controller.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;


class ShopApp {

    ShopApp() {
        port(8888);
        staticFileLocation("/public");

        ProductCategoryController categoryController = new ProductCategoryController();
        SupplierController supplierController = new SupplierController();
        ShoppingCartController cartController = new ShoppingCartController();
        ProductController productController = new ProductController();

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
}