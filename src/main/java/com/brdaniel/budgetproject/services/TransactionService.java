package com.brdaniel.budgetproject.services;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import lombok.Getter;

// This class will handle all business logic for the Transactions List
public class TransactionService {

    // Initialize the DatabaseService
    private final DatabaseService databaseService = new DatabaseService();

    // Lists for displaying into the TableView on the main transaction screen
    // The Observable List is used as the master list
    // It is wrapped in a FilteredList and that list is wrapped in a SortedList
    // The sorted list is then used to display the data in the TableView
    @Getter // getter for the transactionsList for the SummaryService
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

    // Method to sort the list of transactions based on the selected sort option
    // The setComparator method is used to set the comparator for the sorted list
    // Once the comparator is set for the list, it will automatically sort the list based on the selected option
    public void setCategorySort(String category) {
        switch (category) {
            case "Date":
                sortedTransactionsList.setComparator((transaction1, transaction2) -> transaction1.getDate().get().compareTo(transaction2.getDate().get()));
                break;
            case "Amount":
                sortedTransactionsList.setComparator((transaction1, transaction2) -> Double.compare(transaction1.getAmount().get(), transaction2.getAmount().get()));
                break;
            default:
                break;
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

    // Method to get all transactions from the sorted list
    public SortedList<Transaction> getAllTransactions() {
        return sortedTransactionsList;
    }

    // Method to remove a transaction from the database
    public void removeTransaction(Transaction transaction) {
        databaseService.removeTransaction(transaction);
    }

    // Test method to set the observable list for testing purposes
    public void setTransactionsList(ObservableList<Transaction> transactionsList) {
        this.transactionsList.clear();
        this.transactionsList.addAll(transactionsList);
    }
}
