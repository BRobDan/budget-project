package com.brdaniel.budgetproject.services;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.collections.ObservableList;

public class SummaryService {
    
    // Method to get the total income from the transaction service
    public double getTotalIncome(ObservableList<Transaction> transactionsList) {
        double totalIncome = 0;
        // Loop through the transactions list and add up the total income
        for (Transaction transaction : transactionsList) {
            if (transaction.getType().get().equalsIgnoreCase("Income")) { // Check if the transaction is income
                // Adds the amount of the transaction to the total income
                double transactionAmount = transaction.getAmount().get();
                totalIncome += transactionAmount;
            }
        }
        return totalIncome;
    }

    // Method to get the total expenses from the transaction service
    public double getTotalExpenses(ObservableList<Transaction> transactionsList) {
        double totalExpenses = 0;
        // Loop through the transactions list and add up the total expenses
        for (Transaction transaction : transactionsList) {
            if (transaction.getType().get().equalsIgnoreCase("Expense")) { // Check if the transaction is an expense
                // Adds the amount of the transaction to the total expenses
                double transactionAmount = transaction.getAmount().get();
                totalExpenses += transactionAmount;
            }
        }
        return totalExpenses;
    }

    // Method to get the net balance from the transaction service
    public double getNetBalance(ObservableList<Transaction> transactionsList) {
        // Uses the above 2 methods to calculate the net balance and return it
        return getTotalIncome(transactionsList) - getTotalExpenses(transactionsList);
    }
}