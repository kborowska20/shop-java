package com.codecool.shop;

import com.codecool.shop.dao.implementation.SupplierDaoSQLite;
import com.codecool.shop.view.MenuView;

public class Main {
    public static void main(String args[]) {
        SupplierDaoSQLite supDao = new SupplierDaoSQLite();

        supDao.remove(7);

//        MenuView.viewMainMenu();
    }
}
