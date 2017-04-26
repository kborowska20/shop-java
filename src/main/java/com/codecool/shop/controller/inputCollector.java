package com.codecool.shop.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class inputCollector {

    private static Scanner getScanner() {
        // Use getNext* to gather your selected input type.
        return new Scanner(System.in);
    }

    public static Integer getNextInt() throws InputMismatchException {
        Scanner inputScan = getScanner();
        if (inputScan.hasNextInt()) {
            return inputScan.nextInt();
        } else {
            throw new InputMismatchException();
        }
    }

    public static String getNext() throws InputMismatchException {
        Scanner inputScan = getScanner();
        if (inputScan.hasNext()) {
            return inputScan.next();
        } else {
            throw new InputMismatchException();
        }
    }

}
