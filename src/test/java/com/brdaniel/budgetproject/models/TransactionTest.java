package com.brdaniel.budgetproject.models;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

// Class for testing the Transaction model
public class TransactionTest {
    // Test variables
    private Transaction transaction;
    private int id;
    private LocalDate date;
    private String description;
    private double amount;
    private String category;
    private String type;

    // Method to set up the test variables
    @BeforeEach
    public void testSetUp() {
        id = 6;
        date = LocalDate.of(1983, 10, 4); // my birthday :)
        description = "Description";
        amount = 100.0;
        category = "Category";
        type = "Expense";

        transaction = new Transaction(id, date, description, amount, category, type);
    }

    // Test for the constructor and getter methods
    @Test
    public void testTransactionConstructor() {
        Assertions.assertEquals(id, transaction.getId());
        Assertions.assertEquals(date, transaction.getDate().get());
        Assertions.assertEquals(description, transaction.getDescription().get());
        Assertions.assertEquals(amount, transaction.getAmount().get());
        Assertions.assertEquals(category, transaction.getCategory().get());
        Assertions.assertEquals(type, transaction.getType().get());
    }

    // Tests below for each property method in the class
    @Test
    public void testLocalDateProperty() {
        Assertions.assertEquals(date, transaction.localDateProperty().get());
    }

    @Test
    public void testDescriptionProperty() {
        Assertions.assertEquals(description, transaction.descriptionProperty().get());
    }

    @Test
    public void testAmountProperty() {
        Assertions.assertEquals(amount, transaction.amountProperty().get());
    }

    @Test
    public void testCategoryProperty() {
        Assertions.assertEquals(category, transaction.categoryProperty().get());
    }

    @Test
    public void testTypeProperty() {
        Assertions.assertEquals(type, transaction.typeProperty().get());
    }
}
