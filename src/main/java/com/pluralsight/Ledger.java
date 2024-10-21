package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private List<Transaction> transactions;
    private String csvFile;

    // Constructor
    public Ledger(String csvFile) {
        this.transactions = new ArrayList<>();
        this.csvFile = csvFile;
        loadTransactions(); // Load existing transactions from CSV
    }

    // Method to add a transaction and save to CSV
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions(); // Save every time a new transaction is added
    }

    // Method to save all transactions to the CSV file
    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toCSV());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Method to load transactions from the CSV file
    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transaction transaction = new Transaction("Deposit", date, time, description, vendor, amount); // Adjust type accordingly
                    transactions.add(transaction);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    // Getter for all transactions
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // methods to get only deposits and payments
    public List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.toString().contains("Deposit")) {
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    public List<Transaction> getPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.toString().contains("Payment")) {
                payments.add(transaction);
            }
        }
        return payments;
    }
}
