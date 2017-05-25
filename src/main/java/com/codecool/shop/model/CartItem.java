package com.codecool.shop.model;

public class CartItem {

    private static Integer idCount = 0;
    private final Integer id;
    private final Product product;
    private Integer productQuantity;
    private Float totalPrice;

    public CartItem(Product product, Integer productQuantity) {
        id = this.getNextId();
        this.product = product;
        this.productQuantity = productQuantity;
        totalPrice = this.getTotalPrice();
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getId() {
        return this.id;
    }

    private Integer getNextId() {
        return CartItem.idCount++;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
        totalPrice = this.getTotalPrice();
    }

    public void addToProductQuantity(Integer productQuantity) {
        this.productQuantity += productQuantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + this.id +
                ", product=" + this.product +
                ", productQuantity=" + this.productQuantity +
                ", totalPrice=" + this.totalPrice +
                '}';
    }

    Float getTotalPrice() {
        return product.getDefaultPrice() * productQuantity;
    }
}
