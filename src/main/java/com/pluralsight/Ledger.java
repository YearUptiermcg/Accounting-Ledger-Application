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

    public Ledger(String csvFile) {
        this.transactions = new ArrayList<>();
        this.csvFile = csvFile;
        loadTransactions(); // Load transactions from the CSV file on startup
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveToCSV(transaction);
    }

    private void saveToCSV(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            writer.write(transaction.toString());
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    String type = data[0];
                    String date = data[1];
                    String time = data[2];
                    String description = data[3];
                    String vendor = data[4];
                    double amount = Double.parseDouble(data[5]);
                    transactions.add(new Transaction(type, date, time, description, vendor, amount));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
