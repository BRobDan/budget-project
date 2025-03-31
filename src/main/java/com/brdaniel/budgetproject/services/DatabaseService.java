package com.brdaniel.budgetproject.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.brdaniel.budgetproject.models.Transaction;

// This class will handle all database queries and connections
public class DatabaseService {
    // Database connection string and connection object
    private static final String databaseLocation = "jdbc:sqlite:data/database.db";

    // Constructor to initialize the database connection
    public DatabaseService() {
        // Create table if it does not exist
        String createTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "amount REAL NOT NULL," +
                "category TEXT NOT NULL," +
                "type TEXT NOT NULL" +
                ");";
        try (Connection connection = DriverManager.getConnection(databaseLocation); 
            Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a single transaction into the table
    // SQLite should automatically assign an id to the transaction
    public void insertTransaction(LocalDate localDate, String description, double amount, String category, String type) {
        String date = localDate.toString(); // Convert LocalDate to String
        String sql = "INSERT INTO transactions (date, description, amount, category, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseLocation);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            statement.setString(2, description);
            statement.setDouble(3, amount);
            statement.setString(4, category);
            statement.setString(5, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all transactions from the table
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>(); // create a new list to store transactions
        String sql = "SELECT * FROM transactions"; // SQL query to select all transactions

        // try-with-resources statement that automatically closes the statement and result set resources
        try (Connection connection = DriverManager.getConnection(databaseLocation);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            // Loop through the result set and create Transaction objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");

                LocalDate localDate = LocalDate.parse(date); // Convert String to LocalDate
                // Create a new Transaction object and add it to the list
                Transaction transaction = new Transaction(id, localDate, description, amount, category, type);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions; // Return the list of transactions
    }
}
