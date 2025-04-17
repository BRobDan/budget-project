package com.brdaniel.budgetproject.services;

import java.util.HashMap;
import java.util.Map;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


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

    public ObservableList<PieChart.Data> getPieChartData(ObservableList<Transaction> transactionsList) {
        // Create a PieChart.Data object to hold the pie slices
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Create a hashmap to hold each expense transaction and the category it belongs to
        Map<String, Double> expensesMap = new HashMap<>();

        // loop through the transactions list and merge the expenses into the hashmap by category
        for (Transaction transaction : transactionsList) {
            if (transaction.getType().get().equalsIgnoreCase("Expense")) { 
                String category;
                double amount;
                category = transaction.getCategory().get(); // Save the category to a string
                amount = transaction.getAmount().get(); // Save the amount to a double
                expensesMap.merge(category, amount, Double::sum); // merges by summing the amount if the category already exists
            }
        }

        // Loop through the hashmap and add PieChart.Data objects to the list
        for (Map.Entry<String, Double> entry : expensesMap.entrySet()) {
            String category;
            double amount;
            category = entry.getKey(); // Save the category to a string
            amount = entry.getValue(); // Save the amount to a double
            pieChartData.add(new PieChart.Data(category, amount)); // Add the PieChart.Data object to the list
        }

        return pieChartData; // Return the pie chart data list
    }
}