package com.codecool.shop;

import com.codecool.shop.controller.*;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.view.MenuView;
import com.codecool.shop.view.ShoppingCartView;

import java.util.InputMismatchException;

public class Main {

    private static Boolean isMenuLoopActive = true;

    public static void main(String args[]) {
        ShoppingCart shoppingCart = new ShoppingCart();

        while (isAppRunning()) {
            MenuController.getMenu();

            try {
                Integer mainMenuInput = InputCollector.getNextInt();

                switch (mainMenuInput) {
                    case 1:
                        // FIXME #1: NullPointerException
                        ProductController.showAllProducts();
                        break;
                    case 2:
                        // FIXME #2: NullPointerException when ID entered is out of range
                        ProductController.getProductsByCategory();
                        break;
                    case 3:
                        ProductCategoryController.showAllCategories();
                        break;
                    case 4:
                        // FIXME #3: NullPointerException when ID entered is out of range
                        ProductController.getProductsBySupplier();
                        break;
                    case 5:
                        // View supplier
                        SupplierController.showAllSuppliers();
                        break;
                    case 6:
                        shoppingCartMenu(shoppingCart);
                        break;
                    case 0:
                        stopApp();
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
                        // FIXME #4: NullPointerException when trying to add an item to cart
                        ShoppingCartController.addToCart(shoppingCart);
                        break;
                    case 3:
                        ShoppingCartController.removeFromCart(shoppingCart);
                        break;
                    case 4:
                        // FIXME #5: Catch ArithmethicException in menu
                        ShoppingCartController.editQuantity(shoppingCart);
                        break;
                    case 5:
                        // FIXME #6: Look into foolproofing
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
            } catch (ArrayIndexOutOfBoundsException a) {
                MenuView.printMessage("No product with such ID or quantity is too big!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void stopApp() {
        isMenuLoopActive = false;
    }

    private static Boolean isAppRunning() {
        return isMenuLoopActive;
    }
}