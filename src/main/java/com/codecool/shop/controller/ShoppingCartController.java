package com.codecool.shop.controller;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartController extends BaseController {
    private ShoppingCart shoppingCart;
    private ProductController productController;

    public ShoppingCartController(Connection conn, ProductController productController) {
        super(conn);
        this.shoppingCart = new ShoppingCart();
        this.productController = productController;
    }

    public ModelAndView renderCartItems(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("cartItemList", shoppingCart.getItemList());
        params.put("supplierList", getSupplierDao().getAll());

        return new ModelAndView(params, "basket/index");
    }

    public ModelAndView handleAddToCartRequest(Request req, Response res) {
        Integer productID = Integer.parseInt(req.params().get(":pid"));

        if (!(productID == null) || !productID.equals(0)) {
            Product addedProduct = getProductDao().find(productID);
            shoppingCart.addProduct(new CartItem(addedProduct, 1));

            res.status(201);
        } else {
            res.status(400);
        }
        res.redirect("/");
        return productController.renderProducts(req, res);
    }

    public ModelAndView handleRemoveFromCartRequest(Request req, Response res) {
        Integer itemID = Integer.parseInt(req.params().get(":pid"));

        if (!(itemID == null) || !itemID.equals(0)) {
            shoppingCart.removeProduct(getItemBy(itemID));
            res.status(201);
        } else {
            res.status(400);
        }
        res.redirect("/");
        return productController.renderProducts(req, res);
    }

    private CartItem getItemBy(Integer id) {
        CartItem foundCartItem = null;
        for (CartItem cartItem : shoppingCart.getItemList()) {
            if (cartItem.getId().equals(id)) {
                foundCartItem = cartItem;
                break;
            }
        }
        return foundCartItem;
    }

    public ModelAndView handleEditQuantityRequest(Request req, Response res) {
        Integer itemID = Integer.parseInt(req.params().get(":pid"));
        Integer newItemQuantity = Integer.parseInt(req.params().get("item-quantity"));

        if (!(itemID == null) && newItemQuantity > 0 && newItemQuantity < 250) {
            getItemBy(itemID).setProductQuantity(newItemQuantity);

            res.status(201);
        } else {
            res.status(400);
        }
        res.redirect("/basket");
        return productController.renderProducts(req, res);
    }


    public ModelAndView handleCheckoutRequest(Request req, Response res) {
        shoppingCart.getItemList().clear();

        res.redirect("/", 201);
        return productController.renderProducts(req, res);

    }
}
