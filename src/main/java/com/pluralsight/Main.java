package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static Ledger ledger;

    public static void main(String[] args) {
        ledger = new Ledger("transactions.csv");
        homeScreen();
    }

    static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Error!!! Try again!");
            }
        } while (true);
    }

    static void displayLedgerScreen() {
        Scanner scanner = new Scanner(System.in);
        char option;
        System.out.println("Displaying Ledger Screen...");
        while (true) {
            try {
                System.out.println("Which previous transactions would you like to view?");
                System.out.println("(A) - All");
                System.out.println("(D) - Deposits");
                System.out.println("(P) - Payments");
                System.out.println("(R) - Reports");
                System.out.println("(H) - Go to Home Screen");
                System.out.print("Command: ");
                option = scanner.next().toUpperCase().charAt(0);

                switch (option) {
                    case 'A':
                        displayTransactions(ledger.getAllTransactions());
                        break;
                    case 'D':
                        displayTransactions(ledger.getDeposits());
                        break;
                    case 'P':
                        displayTransactions(ledger.getPayments());
                        break;
                    case 'R':
                        displayReportScreen();
                        break;
                    case 'H':
                        return; // Go back to the Home Screen
                    default:
                        System.out.println("Invalid Character, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error!!! Try again!");
            }
        }
    }

    static void displayReportScreen() {
        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.println("Displaying Report Screen...");
        while (true) {
            try {
                System.out.println("Which reports would you like to view?");
                System.out.println("(1) - Month to Date");
                System.out.println("(2) - Previous Month");
                System.out.println("(3) - Year To Date");
                System.out.println("(4) - Previous Year");
                System.out.println("(5) - Search by Vendor");
                System.out.println("(0) - Go back to Ledger Screen");
                System.out.print("Command: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Month-to-Date Transactions:");
                        displayTransactions(ledger.getMonthToDateTransactions());
                        break;
                    case 2:
                        System.out.println("Previous Month Transactions:");
                        displayTransactions(ledger.getPreviousMonthTransactions());
                        break;
                    case 3:
                        System.out.println("Year-to-Date Transactions:");
                        displayTransactions(ledger.getYearToDateTransactions());
                        break;
                    case 4:
                        System.out.println("Previous Year Transactions:");
                        displayTransactions(ledger.getPreviousYearTransactions());
                        break;
                    case 5:
                        System.out.print("Enter vendor name to search: ");
                        scanner.nextLine(); // Consume leftover newline
                        String vendor = scanner.nextLine();
                        displayTransactions(ledger.searchByVendor(vendor));
                        break;
                    case 0:
                        return; // Go back to the Ledger Screen
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error!!! Try again!");
                scanner.next(); // Clear invalid input
            }
        }
    }

    static void displayDepositScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Enter Deposit Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(deposit);
        System.out.println("Deposit recorded: " + deposit.toCSV());
    }

    static void displayPaymentScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Enter Payment Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        Transaction payment = new Transaction(date, time, description, vendor, -Math.abs(amount)); // Ensure payment is negative
        ledger.addTransaction(payment);
        System.out.println("Payment recorded: " + payment.toCSV());
    }

    static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toCSV());
            }
        }
    }
}
