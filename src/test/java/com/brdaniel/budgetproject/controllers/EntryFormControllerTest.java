package com.brdaniel.budgetproject.controllers;

import com.brdaniel.budgetproject.services.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Test class for the entry form controller
// I chose to leave out a test for the handleEntryButton method because it is more complex than the others
// I will test the CRUD functionality in the other service classes
// I also had to remove the test for the passTransaction method because it required me to mock the JavaFX components
public class EntryFormControllerTest {
    private EntryFormController entryFormController;
    private TransactionService transactionService;

    @BeforeEach
    public void testSetUp() {
        // Initialize the EntryFormController and TransactionService
        entryFormController = new EntryFormController();
        transactionService = new TransactionService();
        entryFormController.passTransactionService(transactionService);
    }

    // Test for constructor variables
    @Test
    public void testEntryFormControllerConstructor() {
        // Check initial transactionId value
        Assertions.assertEquals(0, entryFormController.getTransactionId());
        // Check initial isUpdate value
        Assertions.assertFalse(entryFormController.getIsUpdate());
    }

    // Test method for the passTransactionService method
    @Test
    public void testPassTransactionService() {
        Assertions.assertNotNull(entryFormController.getTransactionService());
    }

}
