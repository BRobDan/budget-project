package com.brdaniel.budgetproject.controllers;

import com.brdaniel.budgetproject.services.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Test class for the summary page controller
public class SummaryPageControllerTest {

    private SummaryPageController summaryPageController;
    private TransactionService transactionService;

    @BeforeEach
    public void testSetUp() {
        // Initialize the SummaryPageController and TransactionService
        summaryPageController = new SummaryPageController();
        transactionService = new TransactionService();
        summaryPageController.passTransactionService(transactionService);
    }

    // Test for constructor variables
    @Test
    public void testSummaryPageControllerConstructor() {
        // Check if the summary service is initialized correctly
        Assertions.assertNotNull(summaryPageController.getSummaryService());
    }

    // Test method for the passTransactionService method
    @Test
    public void testPassTransactionService() {
        // Check if the transaction service is passed correctly
        Assertions.assertNotNull(summaryPageController.getTransactionService());
    }
}
