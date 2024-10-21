package com.pluralsight;

public class Transaction {
    private String type;      // "Deposit" or "Payment"
    private String date;      // Format: YYYY-MM-DD
    private String time;      // Format: HH:MM:SS
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String type, String date, String time, String description, String vendor, double amount) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + "," + date + "," + time + "," + description + "," + vendor + "," + amount;
    }

    // Getters and Setters can be added here if needed
}
