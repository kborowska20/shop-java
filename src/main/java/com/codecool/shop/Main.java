package com.codecool.shop;

import com.codecool.shop.dao.implementation.DbInitializer;

import java.sql.SQLException;

public class Main {

    public static void main(String args[]) {

        try {
            DbInitializer dbInit = new DbInitializer();
            for (String arg : args) {
                if (arg.equals("--init-db")) {
                    dbInit.initDb();
                } else if (arg.equals("--migrate-db")) {
                    dbInit.migrateDb();
                }
            }
        } catch (InterruptedException e) {
            System.exit(0);
        }

        ShopApp shopApp = new ShopApp();

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing db connection...");
            try {
                shopApp.getShopApp().closeConnection();
                System.out.println("Connection closed. \n Shutting down...");
                mainThread.join();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}