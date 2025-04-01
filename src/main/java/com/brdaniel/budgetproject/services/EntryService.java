package com.brdaniel.budgetproject.services;

import java.time.LocalDate;

// This class will handle all business logic for the Entry Form
public class EntryService {
    private final DatabaseService databaseService = new DatabaseService();

    // Method to add a transaction to the database
    public void addTransaction(LocalDate localDate, String description, double amount, String category, String type) {
        databaseService.insertTransaction(localDate, description, amount, category, type);
    }

    // Method to validate the date input
    public boolean validateDate(LocalDate localDate) {
        // Check to make sure the date is not in the future
        boolean isValid = !localDate.isAfter(LocalDate.now());
        return isValid;
    }

    // Method to validate the amount input
    public boolean validateAmount(double amount) {
        // Check to make sure the amount is not negative
        boolean isValid = amount >= 0;
        return isValid;
    }
}
