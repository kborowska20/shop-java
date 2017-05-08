
package com.codecool.shop;

import static spark.Spark.*;

import com.codecool.shop.controller.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

class ShopApp {

    private ProductController productController;
    private ProductCategoryController categoryController;
    private SupplierController supplierController;
    private ShoppingCartController cartController;

    public ShopApp() {
        port(8888);
        staticFileLocation("/public");

        categoryController = new ProductCategoryController();
        supplierController = new SupplierController();
        cartController = new ShoppingCartController();
        productController = new ProductController();
//
//        post("basket/:id/add", cartController::handleAddToCartRequest, new ThymeleafTemplateEngine());
//
//        get("basket/:id/remove", cartController::handleRemoveFromCartRequest, new ThymeleafTemplateEngine());
//
//        get("basket/:id/edit", cartController::handleEditQuantityRequest, new ThymeleafTemplateEngine());

        get("/category/:id", productController::renderAllProducts, new ThymeleafTemplateEngine());

        get("/supplier/:id", productController::renderAllProducts, new ThymeleafTemplateEngine());
//
//        get("basket/checkout", cartController::renderItemCheckout, new ThymeleafTemplateEngine());
//
//        post("basket/checkout", cartController::handleCheckoutRequest, new ThymeleafTemplateEngine());
//
//        get("/categories", categoryController::renderAllCategories, new ThymeleafTemplateEngine());
//
//        get("/suppliers", supplierController::renderAllSuppliers, new ThymeleafTemplateEngine());
//
//        get("/basket", cartController::renderCartItems, new ThymeleafTemplateEngine());

        get("/", productController::renderAllProducts, new ThymeleafTemplateEngine());

    }
}