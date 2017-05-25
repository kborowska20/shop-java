package com.codecool.shop.model;

public class CartItem {

    private static Integer idCount = 0;
    private final Integer id;
    private final Product product;
    private Integer productQuantity;
    private Float totalPrice;

    public CartItem(Product product, Integer productQuantity) {
        this.id = getNextId();
        this.product = product;
        this.productQuantity = productQuantity;
        this.totalPrice = getTotalPrice();
    }

    public Product getProduct() {
        return product;
    }

    public Integer getId() {
        return id;
    }

    private Integer getNextId() {
        return idCount++;
    }

    public Integer getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
        this.totalPrice = getTotalPrice();
    }

    public void addToProductQuantity(Integer productQuantity) {
        this.productQuantity += productQuantity;
        this.totalPrice = getTotalPrice();

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

    Float getTotalPrice() {
        return this.product.getDefaultPrice() * this.productQuantity;
    }
}
