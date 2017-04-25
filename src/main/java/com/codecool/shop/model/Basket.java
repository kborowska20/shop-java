package com.codecool.shop.model;

import com.codecool.shop.dao.Iterator;

import java.util.ArrayList;

public class Basket {

    private ArrayList<Product> productList = new ArrayList<>();

    private class ProductIterator implements Iterator {

        int index;

        @Override
        public Boolean hasNext() {
            return index < productList.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return productList.get(index++);
            }

            return null;
        }
    }

    public Iterator getIterator() {
        return new ProductIterator();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public Boolean removeProduct(Product product) {
        Iterator iterator = this.getIterator();
        while (iterator.hasNext()) {
            if (product == iterator.next()) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}