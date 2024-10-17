package com.pluralsight;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HomeScreen();
    }

    /* First Level Menu, exits to end program. */
    static void HomeScreen() {
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
                        return; // Exits the loop and the method
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
                        displayAll();
                        break;
                    case 'D':
                        displayDeposits();
                        break;
                    case 'P':
                        displayPayments();
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
                        System.out.println("Displaying Month to Date Report...");
                        break;
                    case 2:
                        System.out.println("Displaying Previous Month Report...");
                        break;
                    case 3:
                        System.out.println("Displaying Year to Date Report...");
                        break;
                    case 4:
                        System.out.println("Displaying Previous Year Report...");
                        break;
                    case 5:
                        doSearch();
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
        System.out.println("Displaying Deposit Screen...");
        getUserDepositInformation();
    }

    static void displayPaymentScreen() {
        System.out.println("Displaying Payment Screen...");
        getUserPaymentInformation();
    }

    static void displayAll() {
        System.out.println("Displaying All Transactions...");
        // Logic to display all transactions will go here
    }

    static void displayDeposits() {
        System.out.println("Displaying Only Deposits...");
        // Logic to display only deposits will go here
    }

    static void displayPayments() {
        System.out.println("Displaying Only Payments...");

    }

    static void doSearch() {
        System.out.println("Performing Search by Vendor...");

    }

    public static void getUserDepositInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Deposit Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        System.out.println("Deposit recorded: " + description + ", " + vendor + ", $" + amount);

    }

    public static void getUserPaymentInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Payment Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter Amount : ");
        double amount = scanner.nextDouble();


        }
    }

