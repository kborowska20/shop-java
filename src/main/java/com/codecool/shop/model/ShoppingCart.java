package com.codecool.shop.model;

import com.codecool.shop.dao.Iterator;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<CartItem> itemList = new ArrayList<>();

    private class ProductIterator implements Iterator {

        int index;

        @Override
        public Boolean hasNext() {
            return index < itemList.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return itemList.get(index++);
            }

            return null;
        }
    }

    private Iterator getIterator() {
        return new ProductIterator();
    }

    public Float calculateCheckoutPrice() {
        Float checkoutPrice = 0f;
        for (CartItem cartItem : getItemList()) {
            checkoutPrice += cartItem.getTotalPrice();
        }
        System.out.println(checkoutPrice);
        return checkoutPrice;
    }

    public void addProduct(CartItem cartItem) {
        if (itemList.contains(cartItem)) {
            for (CartItem addedItem : itemList) {
                if (addedItem == cartItem) {
                    addedItem.addToProductQuantity(cartItem.getProductQuantity());
                }
            }
        } else {
            itemList.add(cartItem);
        }
    }

    public Boolean removeProduct(CartItem cartItem) {
        Iterator iterator = this.getIterator();
        while (iterator.hasNext()) {
            if (cartItem == iterator.next()) {
                itemList.remove(cartItem);
                return true;
            }
        }
        return false;
    }

    public ArrayList<CartItem> getItemList() {
        return itemList;
    }
}