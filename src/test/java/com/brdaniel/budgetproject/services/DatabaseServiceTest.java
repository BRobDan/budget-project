package com.brdaniel.budgetproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.brdaniel.budgetproject.models.Transaction;
import java.time.LocalDate;
import java.util.List;

// JUnit test class for DatabaseService
// I was originally going to try to use a database saved in the memory, but I couldn't get it to work
// So I settled for testing the actual database.db file in the /data/ folder
// This isn't ideal, but I ensured that the database is reset to it's original state after each test
public class DatabaseServiceTest {

    private DatabaseService databaseService;

    // Set up method to be run before each test
    @BeforeEach
    public void setUp() {
        // Create a new instance of DatabaseService before each test
        databaseService = new DatabaseService();
    }

    // Test to check insertion in database
    @Test
    public void testInsertAndRemoveTransaction() {
        // get the count of the number of transactions in the database
        int transactionCount;
        List<Transaction> transactions = databaseService.getAllTransactions();
        transactionCount = transactions.size();

        // create variables to insert
        LocalDate date = LocalDate.of(1983, 10, 4);
        String description = "Description";
        double amount = 100.0;
        String category = "Category";
        String type = "Type";

        // Insert the transaction into the database
        databaseService.insertTransaction(date, description, amount, category, type);

        // Retrieve a list of the transactions from the database
        transactions = databaseService.getAllTransactions();

        // Check to see if only 1 transaction was inserted in the database
        transactionCount += 1;
        Assertions.assertEquals(transactionCount, transactions.size());

        // Check to see if the transaction data was inserted correctly
        Transaction transaction = transactions.get(transactionCount - 1);
        Assertions.assertEquals(date, transaction.localDateProperty().get());
        Assertions.assertEquals(description, transaction.descriptionProperty().get());
        Assertions.assertEquals(amount, transaction.amountProperty().get());
        Assertions.assertEquals(category, transaction.categoryProperty().get());
        Assertions.assertEquals(type, transaction.typeProperty().get());

        // Remove the transaction from the database
        databaseService.removeTransaction(transaction);

        // Update the transactions list
        transactions = databaseService.getAllTransactions();

        // Check to see if the transaction was removed from the database
        transactionCount -= 1;
        Assertions.assertEquals(transactionCount, transactions.size());
    }

    // Test to check if the updateTransaction method works
    @Test
    public void testUpdateTransaction() {
        // get the count of the number of transactions in the database
        int transactionsCount;
        List<Transaction> transactions = databaseService.getAllTransactions();
        transactionsCount = transactions.size();

        // create variables to insert
        LocalDate date = LocalDate.of(1983, 10, 4);
        String description = "Description";
        double amount = 100.0;
        String category = "Category";
        String type = "Type";

        // Insert the transaction into the database
        transactionsCount += 1; // update counter
        databaseService.insertTransaction(date, description, amount, category, type);

        // Retrieve a list of the transactions from the database
        transactions = databaseService.getAllTransactions();

        // Check to see if only 1 transaction was inserted in the database
        Assertions.assertEquals(transactionsCount, transactions.size());

        // Update the transaction in the database
        int id = transactions.get(transactionsCount - 1).getId(); // Get the id of the transaction to update
        LocalDate updatedDate = LocalDate.of(1111, 11, 11);
        String updatedDescription = "New Description";
        double updatedAmount = 200.0;
        String updatedCategory = "New Category";
        String updatedType = "New Type";

        databaseService.updateTransaction(id, updatedDate, updatedDescription, updatedAmount, updatedCategory, updatedType);

        // Retrieve a list of the transactions from the database
        transactions = databaseService.getAllTransactions();

        // Check to see if only 1 transaction was inserted in the database
        Assertions.assertEquals(transactionsCount, transactions.size());

        // Check to see if the transaction data was updated correctly
        Transaction updatedTransaction = transactions.get(transactionsCount - 1);
        Assertions.assertEquals(id, updatedTransaction.getId());
        Assertions.assertEquals(updatedDate, updatedTransaction.localDateProperty().get());
        Assertions.assertEquals(updatedDescription, updatedTransaction.descriptionProperty().get());
        Assertions.assertEquals(updatedAmount, updatedTransaction.amountProperty().get());
        Assertions.assertEquals(updatedCategory, updatedTransaction.categoryProperty().get());
        Assertions.assertEquals(updatedType, updatedTransaction.typeProperty().get());

        // Remove the transaction from the database
        transactionsCount -= 1; // update counter
        databaseService.removeTransaction(updatedTransaction);

        // Check to ensure the test transaction was removed
        transactions = databaseService.getAllTransactions();
        Assertions.assertEquals(transactionsCount, transactions.size());
    }
}
