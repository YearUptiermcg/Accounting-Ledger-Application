package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private String csvFilePath;
    private List<Transaction> transactions;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Ledger(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        this.transactions = new ArrayList<>();
        loadTransactions();
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions from file: " + e.getMessage());
        }
    }

    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(transaction.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    public List<Transaction> getPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }
        return payments;
    }

    // Retrieve transactions for Month-to-Date
    public List<Transaction> getMonthToDateTransactions() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        return filterTransactionsByDateRange(startOfMonth, now);
    }

    // Retrieve transactions for Previous Month
    public List<Transaction> getPreviousMonthTransactions() {
        LocalDate now = LocalDate.now();
        LocalDate startOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = startOfLastMonth.withDayOfMonth(startOfLastMonth.lengthOfMonth());
        return filterTransactionsByDateRange(startOfLastMonth, endOfLastMonth);
    }

    // Retrieve transactions for Year-to-Date
    public List<Transaction> getYearToDateTransactions() {
        LocalDate now = LocalDate.now();
        LocalDate startOfYear = now.withDayOfYear(1);
        return filterTransactionsByDateRange(startOfYear, now);
    }

    // Retrieve transactions for Previous Year
    public List<Transaction> getPreviousYearTransactions() {
        LocalDate now = LocalDate.now();
        LocalDate startOfLastYear = now.minusYears(1).withDayOfYear(1);
        LocalDate endOfLastYear = startOfLastYear.withDayOfYear(startOfLastYear.lengthOfYear());
        return filterTransactionsByDateRange(startOfLastYear, endOfLastYear);
    }

    private List<Transaction> filterTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            if ((transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate)) &&
                    (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate))) {
                filteredTransactions.add(transaction);
            }
        }

        // Show the dates being filtered
        System.out.println("Filtering from " + startDate + " to " + endDate);
        System.out.println("Transactions found: " + filteredTransactions.size());

        return filteredTransactions;
    }

    public List<Transaction> searchByVendor(String vendor) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                results.add(transaction);
            }
        }
        return results;
    }
}