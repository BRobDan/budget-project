package com.brdaniel.budgetproject.services;

import java.time.LocalDate;
import com.brdaniel.budgetproject.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Test class for the SummaryService
// I'm just testing the logic in the SummaryService class
// The pie chart is not tested because it a GUI component
public class SummaryServiceTest {

    private SummaryService summaryService;
    private ObservableList<Transaction> transactionsList;

    // Setup method
    @BeforeEach
    public void setUp() {
        // Initialize the SummaryService and transactionsList before each test
        summaryService = new SummaryService();
        transactionsList = FXCollections.observableArrayList();

        // Set up some test data for readability
        LocalDate date = LocalDate.of(2025, 1, 1);
        int id = 1;

        // Add some test transactions
        transactionsList.add(new Transaction(id, date,"Salary", 1000, "Other","Income"));
        transactionsList.add(new Transaction(id + 1, date.plusDays(1), "Salary", 1000, "Other", "Income"));
        transactionsList.add(new Transaction(id + 2, date.plusDays(2), "Groceries", 300, "Other","Expense"));
        transactionsList.add(new Transaction(id + 3, date.plusDays(3),"Groceries", 200, "Other", "Expense"));
    }

    // Test method for getTotalIncome
    @Test
    public void testGetTotalIncome() {
        // Call the method for total income
        double totalIncome = summaryService.getTotalIncome(transactionsList);

        // Check that the income equals 2000
        Assertions.assertEquals(2000, totalIncome);
    }

    // Test method for getTotalExpenses
    @Test
    public void testGetTotalExpenses() {
        // Call the method for total expenses
        double totalExpenses = summaryService.getTotalExpenses(transactionsList);

        // Check that the expenses equal 500
        Assertions.assertEquals(500, totalExpenses);
    }

    // Test method for getNetBalance
    @Test
    public void testGetNetBalance() {
        // Call the method for net balance
        double netBalance = summaryService.getNetBalance(transactionsList);

        // Check that the net balance equals 1500
        Assertions.assertEquals(1500, netBalance);
    }
}
