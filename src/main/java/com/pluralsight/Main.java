package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Ledger ledger = new Ledger("transactions.csv");

    public static void main(String[] args) {
        HomeScreen();
    }

    static void HomeScreen() {
        char option;

        do {
            try {
                System.out.println();
                System.out.println("----------Home Screen-----------");
                System.out.println("Please select from the following:");
                System.out.println("(D) Add Deposit");
                System.out.println("(P) Make Payment");
                System.out.println("(L) Ledger");
                System.out.println("(X) Exit");
                System.out.print("Command: ");
                option = scanner.next().toUpperCase().charAt(0);

                switch (option) {
                    case 'D': // Add Deposit
                        displayDepositScreen();
                        break;
                    case 'P': // Make Payment
                        displayPaymentScreen();
                        break;
                    case 'L': // Ledger Screen
                        displayLedgerScreen();
                        break;
                    case 'X': // Exit
                        System.out.println("Exiting the application. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid Character, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error!!! Try again! " + e.getMessage());
                scanner.next();
            }
        } while (true);
    }

    static void displayLedgerScreen() {
        System.out.println("Displaying Ledger Screen...");
        // Load and display transactions from the CSV file
        List<Transaction> transactions = ledger.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
        // Returning to home screen after displaying
        System.out.println("Press any key to go back to Home Screen...");
        scanner.next(); // Wait for user input
    }

    static void displayDepositScreen() {
        System.out.println("Displaying Deposit Screen...");
        getUserDepositInformation();
    }

    static void displayPaymentScreen() {
        System.out.println("Displaying Payment Screen...");
        getUserPaymentInformation();
    }

    public static void getUserDepositInformation() {
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.next();

        System.out.print("Enter Time (HH:MM:SS): ");
        String time = scanner.next();

        System.out.print("Enter Deposit Description: ");
        String description = scanner.next();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.next();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        // Create and add the deposit transaction to the ledger
        Transaction deposit = new Transaction("Deposit", date, time, description, vendor, amount);
        ledger.addTransaction(deposit);

        System.out.println("Deposit recorded: " + deposit);
    }

    public static void getUserPaymentInformation() {
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.next();

        System.out.print("Enter Time (HH:MM:SS): ");
        String time = scanner.next();

        System.out.print("Enter Payment Description: ");
        String description = scanner.next();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.next();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        // Create and add the payment transaction to the ledger
        Transaction payment = new Transaction("Payment", date, time, description, vendor, amount);
        ledger.addTransaction(payment);

        System.out.println("Payment recorded: " + payment);
    }
}
