package com.brdaniel.budgetproject.services;

import java.util.List;

import com.brdaniel.budgetproject.models.Transaction;

// This class will handle all business logic for the Transactions List
public class TransactionService {

    // Initialize the DatabaseService
    private final DatabaseService databaseService = new DatabaseService();

    // Method to get all transactions from the database
    public List<Transaction> getAllTransactions() {
        return databaseService.getAllTransactions();
    }

    
}
