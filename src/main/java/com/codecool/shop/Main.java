package com.codecool.shop;

import com.codecool.shop.controller.*;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.ShoppingCartView;

public class Main {
    public static void main(String args[]) {
        ShoppingCart shoppingCart = new ShoppingCart();

        Boolean isMenuLoopActive = true;
        while (isMenuLoopActive) {
            MenuController.getMenu();

            try {
                Integer mainMenuInput = InputCollector.getNextInt();

                switch (mainMenuInput) {
                    case 1:
                        // So that I can browse Products within that Category
                        ProductController.showAllProducts();
                        break;
                    case 2:
                        // So that I can browse Products within any Category
                        ProductCategoryController.showAllCategories();
                        MenuController.showMessage("Please enter ID of the category: ");
                        Integer userCategoryInput = InputCollector.getNextInt();
                        ProductController.getProductsByCategory(userCategoryInput);
                        break;
                    case 3:
                        // View categories
                        ProductCategoryController.showAllCategories();
                        break;
                    case 4:
                        // So that I can browse Products by Suppliers
                        SupplierController.showAllSuppliers();
                        MenuController.showMessage("Please enter ID of the supplier: ");
                        Integer userSupplierInput = InputCollector.getNextInt();
                        ProductController.getProductsBySupplier(userSupplierInput);
                        break;
                    case 5:
                        // View supplier
                        SupplierController.showAllSuppliers();
                        break;
                    case 6:
                        shoppingCartMenu(shoppingCart);
                        break;
                    case 0:
                        isMenuLoopActive = false;
                        break;
                    default:
                        break;
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
            try {
                Integer cartMenuInput = InputCollector.getNextInt();
                switch (cartMenuInput) {
                    case 0:
                        isShoppingCardMenuLoopActive = false;
                        break;
                    case 1:
                        // Show items in my Cart
                        ShoppingCartController.showCartItems(shoppingCart);
                        break;
                    case 2:
                        // Add item
                        ProductController.showAllProducts();
                        MenuController.showMessage("Please select ID of the product:");
                        Integer productIdInput = InputCollector.getNextInt();

                        MenuController.showMessage("Please input quantity of the product");
                        Integer productQuantityInput = InputCollector.getNextInt();

                        ShoppingCartController.addToCart(shoppingCart, productIdInput, productQuantityInput);
                        break;
                    case 3:
                        // Remove item
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer itemIdInput = InputCollector.getNextInt();

                        ShoppingCartController.removeFromCart(shoppingCart, itemIdInput);
                        break;
                    case 4:
                        // Change quantity of item
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer cartItemId = InputCollector.getNextInt();

                        MenuController.showMessage("Please input new quantity:");
                        Integer newItemQuantity = InputCollector.getNextInt();

                        ShoppingCartController.editQuantity(shoppingCart, cartItemId, newItemQuantity);
                        break;
                    case 5:
                        // Checkout item
                        break;

                    case 6:
                        // Use Promo Code
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}