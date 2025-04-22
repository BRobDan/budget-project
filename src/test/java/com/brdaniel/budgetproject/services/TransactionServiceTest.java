package com.brdaniel.budgetproject.services;

import com.brdaniel.budgetproject.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

// Test class for the TransactionService
// I am just testing the filtering and sorting logic in the service
// The database service was tested seperately, and I am not mocking the database service
public class TransactionServiceTest {

    TransactionService transactionService;
    ObservableList<Transaction> transactionsList;

    // Setup method
    @BeforeEach
    public void setUp() {
        // Initialize the TransactionService before each test
        transactionService = new TransactionService();
        transactionsList = FXCollections.observableArrayList();

        // Set up some test data for readability
        LocalDate date = LocalDate.of(2025, 1, 5);
        int id = 1;

        // Add some test transactions
        transactionsList.add(new Transaction(id, date,"Salary", 1000, "Other","Income"));
        transactionsList.add(new Transaction(id + 1, date.minusDays(1), "Salary", 1000, "Other", "Income"));
        transactionsList.add(new Transaction(id + 2, date.minusDays(2), "Groceries", 300, "Food","Expense"));
        transactionsList.add(new Transaction(id + 3, date.minusDays(3),"Groceries", 200, "Food", "Income"));

        // set the master list in the transaction service to the test data
        transactionService.setTransactionsList(transactionsList);
    }

    // Test method for setCategoryFilter
    // This tests all 3 cases in the method
    @Test
    public void testSetCategoryFilter() {
        // Call the method for category filter
        transactionService.setCategoryFilter("All");

        // Check that the filtered list size equals the transactions list size
        Assertions.assertEquals(transactionsList.size(), transactionService.getAllTransactions().size());

        // Check that the filtered list size equals 3
        transactionService.setCategoryFilter("Income");
        Assertions.assertEquals(3, transactionService.getAllTransactions().size());

        // Check that the filtered list size equals 1
        transactionService.setCategoryFilter("Expense");
        Assertions.assertEquals(1, transactionService.getAllTransactions().size());

        // Check that the filtered list size equals 2
        transactionService.setCategoryFilter("Food");
        Assertions.assertEquals(2, transactionService.getAllTransactions().size());
    }

    // Test method for setCategorySort
    // This tests all 2 cases in the method
    @Test
    public void testSetCategorySort() {
        // test variables (earliest date and lowest amount for testing)
        LocalDate date = LocalDate.of(2025, 1, 5).minusDays(3);
        double lowestAmount = 200;

        // Check that the method sorts by date properly
        transactionService.setCategorySort("Date");
        Assertions.assertEquals(date, transactionService.getAllTransactions().get(0).getDate().get());

        // Check that the sorted list is sorted by amount
        transactionService.setCategorySort("Amount");
        Assertions.assertEquals(lowestAmount, transactionService.getAllTransactions().get(0).getAmount().get());
    }
}
