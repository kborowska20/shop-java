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

    public void addProduct(CartItem cartItem) {
        itemList.add(cartItem);
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