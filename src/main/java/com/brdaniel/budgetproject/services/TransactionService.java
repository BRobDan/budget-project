package com.brdaniel.budgetproject.services;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

// This class will handle all business logic for the Transactions List
public class TransactionService {

    // Initialize the DatabaseService
    private final DatabaseService databaseService = new DatabaseService();

    // Lists for displaying into the TableView on the main transaction screen
    // The Observable List is used as the master list
    // It is wrapped in a FilteredList and that list is wrapped in a SortedList
    // The sorted list is then used to display the data in the TableView
    private final ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();
    private final FilteredList<Transaction> filteredTransactionsList = new FilteredList<>(transactionsList, transaction -> true);
    private final SortedList<Transaction> sortedTransactionsList = new SortedList<>(filteredTransactionsList);

    // Method to filter the list of transactions based on the selected filter option
    public void setCategoryFilter(String category) {
        // If the category is "All", include all transactions and return
        if (category.equalsIgnoreCase("All")) {
            filteredTransactionsList.setPredicate(transaction -> true);
            return;
        }

        // If the category is Income/Expense, then filter by type
        if (category.equalsIgnoreCase("Income") || category.equalsIgnoreCase("Expense")) {
            filteredTransactionsList.setPredicate(transaction -> transaction.getType().get().equalsIgnoreCase(category));
        } else {
            // All other options mean that it is filtered by category
            filteredTransactionsList.setPredicate(transaction -> transaction.getCategory().get().equalsIgnoreCase(category));
        }
    }

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
    public SortedList<Transaction> getAllTransactions() {
        return sortedTransactionsList;
    }

    // Method to remove a transaction from the database
    public void removeTransaction(Transaction transaction) {
        databaseService.removeTransaction(transaction);
    }

    
}
