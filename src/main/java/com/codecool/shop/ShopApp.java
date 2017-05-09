
package com.codecool.shop;

import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.ShoppingCartController;
import com.codecool.shop.controller.SupplierController;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

class ShopApp {

    private ProductController productController;
    private ProductCategoryController categoryController;
    private SupplierController supplierController;
    private ShoppingCartController cartController;

    ShopApp() {
        port(8888);
        staticFileLocation("/public");

        categoryController = new ProductCategoryController();
        supplierController = new SupplierController();
        cartController = new ShoppingCartController();
        productController = new ProductController();

//        post("basket/:id/add", cartController::handleAddToCartRequest, new ThymeleafTemplateEngine());

//        get("basket/:id/remove", cartController::handleRemoveFromCartRequest, new ThymeleafTemplateEngine());

//        get("basket/:id/edit", cartController::handleEditQuantityRequest, new ThymeleafTemplateEngine());

        get("/category/:cID", productController::renderProducts, new ThymeleafTemplateEngine());

        get("/supplier/:sID", productController::renderProducts, new ThymeleafTemplateEngine());

//        get("basket/checkout", cartController::renderItemCheckout, new ThymeleafTemplateEngine());

//        post("basket/checkout", cartController::handleCheckoutRequest, new ThymeleafTemplateEngine());

//        get("/categories", categoryController::renderAllCategories, new ThymeleafTemplateEngine());

//        get("/suppliers", supplierController::renderAllSuppliers, new ThymeleafTemplateEngine());

//        get("/basket", cartController::renderCartItems, new ThymeleafTemplateEngine());

        get("/", productController::renderProducts, new ThymeleafTemplateEngine());

    }
}