package com.codecool.shop;

import com.codecool.shop.controller.*;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.MenuView;
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
                    case 1:
                        ProductController.showAllProducts();
                        break;
                    case 2:
                        ProductCategoryController.showAllCategories();
                        MenuController.showMessage("Please enter ID of the category: ");
                        Integer userCategoryInput = InputCollector.getNextInt();
                        ProductController.getProductsByCategory(userCategoryInput);
                        break;
                    case 3:
                        ProductCategoryController.showAllCategories();
                        break;
                    case 4:
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
            } catch (InputMismatchException i) {
                MenuView.printMessage("Invalid input.");
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
                        ShoppingCartController.showCartItems(shoppingCart);
                        break;
                    case 2:
                        ProductController.showAllProducts();
                        MenuController.showMessage("Please select ID of the product:");
                        Integer productIdInput = InputCollector.getNextInt();

                        MenuController.showMessage("Please input quantity of the product (between 1 and 250)");
                        Integer productQuantityInput = InputCollector.getNextInt();
                        try {
                            ShoppingCartController.addToCart(shoppingCart, productIdInput, productQuantityInput);
                        } catch (ArrayIndexOutOfBoundsException a) {
                            MenuView.printMessage("No product with such ID or quantity is too big!");
                        }
                        break;
                    case 3:
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer itemIdInput = InputCollector.getNextInt();

                        ShoppingCartController.removeFromCart(shoppingCart, itemIdInput);
                        break;
                    case 4:
                        ShoppingCartController.showCartItems(shoppingCart);
                        MenuController.showMessage("Please select ID of the item:");
                        Integer cartItemId = InputCollector.getNextInt();

                        MenuController.showMessage("Please input new quantity:");
                        Integer newItemQuantity = InputCollector.getNextInt();

                        ShoppingCartController.editQuantity(shoppingCart, cartItemId, newItemQuantity);
                        break;
                    case 5:
                        CheckoutController.checkoutItems(shoppingCart);
                        break;
                    case 6:
                        // Use Promo Code
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException i) {
                MenuView.printMessage("Invalid input.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}