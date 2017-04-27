package com.codecool.shop.model;

public class CartItem {

    private static Integer idCount = 0;
    private Integer id;
    private Product product;
    private Integer productQuantity;
    private Float totalPrice;

    public CartItem(Product product, Integer productQuantity) {
        this.id = getNextId();
        this.product = product;
        this.productQuantity = productQuantity;
        this.totalPrice = getTotalPrice();
    }

    public Integer getId() {
        return id;
    }

    private Integer getNextId() {
        return idCount++;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", productQuantity=" + productQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    private Float getTotalPrice() {
        return this.product.getDefaultPrice() * this.productQuantity;
    }

    private Integer getCheckoutPrice() {
        return null;
    }
}
