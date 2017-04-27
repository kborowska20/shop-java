package com.codecool.shop.view;

import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierView {
    public static void printSupplierList(List<Supplier> supplierList) {
        for (Supplier supplier : supplierList) {
            printSupplier(supplier);
        }
    }

    public static void printSupplier(Supplier supplier) {
        System.out.println(supplier.toString());
    }
}
