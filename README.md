# **Accounting Ledger Application**

## **Project Overview**

This is a basic Java application that simulates a financial ledger system, allowing users to:
- Record deposits and payments.
- View all transactions, deposits only, or payments only.
- Generate reports for transactions based on different criteria (month-to-date, previous month, year-to-date, previous year, or by vendor).

The application saves all transactions to a `transactions.csv` file, ensuring data is preserved between sessions.

## **Features**

1. **Home Screen**:
   - Add Deposit
   - Make Payment
   - View Ledger
   - Exit

2. **Ledger Screen**:
   - View all transactions
   - View only deposits
   - View only payments
   - Access report options

3. **Reports**:
   - Month to Date
   - Previous Month
   - Year to Date
   - Previous Year
   - Search by Vendor

## **Components**

- **Transaction Class**: Represents a financial transaction, holding details like date, time, description, vendor, and amount. Includes methods for formatting output.

- **Ledger Class**: Manages the collection of transactions. Handles reading from and writing to the CSV file and provides methods for filtering transactions based on time periods.

- **Main Class**: Provides a user interface via a text-based menu, allowing users to interact with the application.

## **Getting Started**

### **Prerequisites**
- **Java Development Kit (JDK)** installed on your machine.
- **A text editor or IDE** for Java development (e.g., IntelliJ IDEA, Eclipse).

### **Installation**
1. **Clone the repository or download the source code.**
2. **Open the project in your IDE.**
3. **Ensure you have a `transactions.csv` file** in the same directory as the application (it will be created automatically on the first run).

### **Running the Application**
1. **Compile the Java files.**
2. **Run the Main class.**
3. **Follow the on-screen instructions** to manage your financial transactions.

## **Usage**
- **Choose options from the menu** to add deposits, make payments, or view transaction reports.
- **Input validation** ensures that only correct data is accepted.
- **Error messages** will guide you if something goes wrong.


## **License**
This project is licensed under the MIT License .



