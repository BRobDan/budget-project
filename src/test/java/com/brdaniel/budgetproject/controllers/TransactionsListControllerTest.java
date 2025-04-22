package com.brdaniel.budgetproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Test class for TransactionsListController
// The only part of this controller class I can test is the contructor and service initialization
// because the rest of the code is UI related and requires JavaFX to be initialized
public class TransactionsListControllerTest {

    // Initialize the TransactionsListController
    private TransactionsListController transactionsListController;

    // setup method to initialize the TransactionsListController
    @BeforeEach
    public void setUp() {
        // Create a new instance of TransactionsListController before each test
        transactionsListController = new TransactionsListController();
    }

    // Test method for the TransactionsListController constructor
    @Test
    public void transactionsListControllerConstructorTest() {
        // Check if the Transaction Service is initialized correctly
        Assertions.assertNotNull(transactionsListController.getTransactionService());
    }
}
