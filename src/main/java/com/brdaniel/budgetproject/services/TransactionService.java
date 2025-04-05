package com.brdaniel.budgetproject.services;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// This class will handle all business logic for the Transactions List
public class TransactionService {

    // Initialize the DatabaseService
    private final DatabaseService databaseService = new DatabaseService();

    // Observable List for updating transactions in the tableview dynamically
    private final ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();

    // Constructor to initialize the transactions list
    public TransactionService() {
        // Load all transactions from the database into the observable list
        transactionsList.addAll(databaseService.getAllTransactions());
    }

    // Refresh Transaction List
    public void refreshTransactionList() {
        transactionsList.clear(); // Clear the current list
        transactionsList.addAll(databaseService.getAllTransactions()); // Add all transactions from the database
    }

    // Method to get all transactions from the database
    public ObservableList<Transaction> getAllTransactions() {
        return transactionsList;
    }

    
}
