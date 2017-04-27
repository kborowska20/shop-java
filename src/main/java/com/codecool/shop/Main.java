package com.codecool.shop;

import com.codecool.shop.controller.*;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.ShoppingCartView;

import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) {
        ShoppingCart shoppingCart = new ShoppingCart();

        Boolean isMenuLoopActive = true;
        while (isMenuLoopActive) {
            MenuController.getMenu();

            try {
                Integer mainMenuInput = InputCollector.getNextInt();

                switch (mainMenuInput) {
                    default:
                        continue;
                    case 1:
                        // So that I can browse Products within that Category
                        ProductController.showAllProducts();
                        continue;
                    case 2:
                        // So that I can browse Products within any Category
                        ProductCategoryController.showAllCategories();
                        MenuController.showMessage("Please enter ID of the category: ");
                        Integer userCategoryInput = InputCollector.getNextInt();
                        ProductController.getProductsByCategory(userCategoryInput);
                        continue;
                    case 3:
                        // View categories
                        ProductCategoryController.showAllCategories();
                        continue;
                    case 4:
                        // So that I can browse Products by Suppliers
                        SupplierController.showAllSuppliers();
                        MenuController.showMessage("Please enter ID of the supplier: ");
                        Integer userSupplierInput = InputCollector.getNextInt();
                        ProductController.getProductsBySupplier(userSupplierInput);
                        continue;
                    case 5:
                        // View supplier
                        SupplierController.showAllSuppliers();
                        continue;
                    case 6:
                        shoppingCartMenu(shoppingCart);
                        continue;
                    case 0:
                        isMenuLoopActive = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void shoppingCartMenu(ShoppingCart shoppingCart) {
        Boolean isShoppingCardMenuLoopActive = true;
        while (isShoppingCardMenuLoopActive) {
            ShoppingCartView.viewShoppingCardMenu();
            Integer cartMenuInput = InputCollector.getNextInt();
            try {
                switch (cartMenuInput) {
                    case 1:
                        // Show items in my Cart
                        ShoppingCartController.showCartItems(shoppingCart);
                        continue;
                    case 2:
                        // Add item
                        ProductController.showAllProducts();
                        MenuController.showMessage("Please select ID of the product:");
                        Integer productIdInput = InputCollector.getNextInt();

                        MenuController.showMessage("Please input quantity of the product");
                        Integer productQuantityInput = InputCollector.getNextInt();

                        ShoppingCartController.addToCart(shoppingCart, productIdInput, productQuantityInput);
                        continue;
                    case 3:
                        // Remove item
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer itemIdInput = InputCollector.getNextInt();

                        ShoppingCartController.removeFromCart(shoppingCart, itemIdInput);
                        continue;
                    case 4:
                        // Change quantity of item
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer cartItemId = InputCollector.getNextInt();

                        MenuController.showMessage("Please input new quantity:");
                        Integer newItemQuantity = InputCollector.getNextInt();

                        ShoppingCartController.editQuantity(shoppingCart, cartItemId, newItemQuantity);
                        continue;
                    case 5:
                        // Checkout item
                        continue;

                    case 6:
                        // Use Promo Code
                        continue;
                    case 0:
                        isShoppingCardMenuLoopActive = false;
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
    }
}