
package com.codecool.shop;

import static spark.Spark.*;

import com.codecool.shop.controller.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

class ShopApp {

    private ProductController productController = new ProductController();
    private ProductCategoryController categoryController = new ProductCategoryController();
    private SupplierController supplierController = new SupplierController();
    private ShoppingCartController cartController = new ShoppingCartController();

    public ShopApp() {
        port(8888);
        staticFileLocation("/public");

        post("basket/:id/add", cartController::handleAddToCartRequest, new ThymeleafTemplateEngine());

        get("basket/:id/remove", cartController::handleRemoveFromCartRequest, new ThymeleafTemplateEngine());

        get("basket/:id/edit", cartController::handleEditQuantityRequest, new ThymeleafTemplateEngine());

        get("/category/:id", productController::renderAllProducts, new ThymeleafTemplateEngine());

        get("/supplier/:id", productController::renderAllProducts, new ThymeleafTemplateEngine());

        get("basket/checkout", cartController::renderItemCheckout, new ThymeleafTemplateEngine());

        post("basket/checkout", cartController::handleCheckoutRequest, new ThymeleafTemplateEngine());

        get("/categories", categoryController::renderAllCategories, new ThymeleafTemplateEngine());

        get("/suppliers", supplierController::renderAllSuppliers, new ThymeleafTemplateEngine());

        get("/basket", cartController::renderCartItems, new ThymeleafTemplateEngine());

        get("/", productController::renderAllProducts, new ThymeleafTemplateEngine());
    }
}