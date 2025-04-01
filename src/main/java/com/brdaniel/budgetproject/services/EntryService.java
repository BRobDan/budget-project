package com.brdaniel.budgetproject.services;

import java.time.LocalDate;

// This class will handle all business logic for the Entry Form
public class EntryService {
    private final DatabaseService databaseService = new DatabaseService();

    // Method to add a transaction to the database
    public void addTransaction(LocalDate localDate, String description, double amount, String category, String type) {
        databaseService.insertTransaction(localDate, description, amount, category, type);
    }
}
