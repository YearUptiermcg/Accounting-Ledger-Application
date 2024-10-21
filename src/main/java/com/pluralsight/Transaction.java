package com.pluralsight;

public class Transaction {
    private String type; // "Deposit" or "Payment"
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor
    public Transaction(String type, String date, String time, String description, String vendor, double amount) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Method to convert Transaction to CSV format
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }

    // Override toString for better display
    @Override
    public String toString() {
        return type + " - " + date + " " + time + " | " + description + " | " + vendor + " | $" + amount;
    }

    public String getVendor() {
        return vendor;
    }
}
