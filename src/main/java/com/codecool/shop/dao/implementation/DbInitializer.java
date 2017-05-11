package com.codecool.shop.dao.implementation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DbInitializer {
    private Connection conn;

    public DbInitializer() {
        this.conn = new DbConnector().getConnection();
    }

    public void initDb() throws InterruptedException {
        System.out.println("Initializing database. \n Filename: Products.db");
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/db/init.sql"),
                    Charset.defaultCharset());
            List<String> initStatements = Arrays.asList(lines.get(0).split(";"));

            Statement dbStatement = conn.createStatement();
            for (String statement : initStatements) {
                dbStatement.execute(statement);
            }

            dbStatement.close();
        } catch (IOException | SQLException e) {
            System.out.println("Couldn't read statement from init.sql file!");
            e.printStackTrace();
            throw new InterruptedException();
        }
    }

    public void migrateDb() throws InterruptedException {
        System.out.println("Migrating to database. \n Filename: Products.db");
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/db/migrate.sql"),
                    Charset.defaultCharset());

            List<String> initStatements = Arrays.asList(lines.get(0).split(";"));

            Statement dbStatement = conn.createStatement();
            for (String statement : initStatements) {
                dbStatement.executeUpdate(statement);
            }

            dbStatement.close();
        } catch (IOException | SQLException e) {
            System.out.println("Couldn't read statement from migrate.sql file! \n Closing...");
            e.printStackTrace();
            throw new InterruptedException();

        }
    }
}