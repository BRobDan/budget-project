package com.brdaniel.budgetproject.services;

import java.time.LocalDate;
import java.util.List;

import com.brdaniel.budgetproject.models.Transaction;

// This class will handle all business logic for the Transactions List
public class TransactionService {

    // Initialize the DatabaseService
    private DatabaseService databaseService = new DatabaseService();

    // Method to add a transaction to the database
    public void addTransaction(LocalDate localDate, String description, double amount, String category, String type) {
        databaseService.insertTransaction(localDate, description, amount, category, type);
    }

    // Method to get all transactions from the database
    public List<Transaction> getAllTransactions() {
        return databaseService.getAllTransactions();
    }

    
}
