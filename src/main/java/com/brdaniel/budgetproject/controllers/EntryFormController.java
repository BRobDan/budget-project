package com.brdaniel.budgetproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.brdaniel.budgetproject.models.Transaction;
import com.brdaniel.budgetproject.services.EntryService;
import com.brdaniel.budgetproject.services.TransactionService;
import com.brdaniel.budgetproject.view.ErrorWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EntryFormController {
    // initialize the entry service
    private final EntryService entryService = new EntryService();

    // intialize transactionService to pass it to the EntryService
    private TransactionService transactionService;

    // variables to keep track of whether this is an update or a new entry && the id of the transaction
    private boolean isUpdate = false;
    private int transactionId = 0;

    // Method to pass the TransactionService to the EntryService
    // This is used to refresh the transaction list after adding a new transaction
    public void passTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Method to autopopulate all fields for updating a transaction
    public void passTransaction(Transaction transaction) {
        dateTextField.setText(transaction.localDateProperty().get().toString());
        descriptionTextField.setText(transaction.getDescription().get());
        amountTextField.setText(String.valueOf(transaction.getAmount().get()));
        categoryComboBox.setValue(transaction.getCategory().get());
        typeComboBox.setValue(transaction.getType().get());
        // Set isUpdate to true
        isUpdate = true;
        // Set the transactionId to the id of the transaction being updated
        transactionId = transaction.getId();
    }

    // Date TextField controller code
    @FXML
    private TextField dateTextField;

    // Description TextField controller code
    @FXML
    private TextField descriptionTextField;

    // Amount TextField controller code
    @FXML
    private TextField amountTextField;

    // Category ComboBox controller code
    @FXML
    private ComboBox<String> categoryComboBox;

    // Type ComboBox controller code
    @FXML
    private ComboBox<String> typeComboBox;

    // Entry Button controller code
    @FXML
    private Button entryButton;

    @FXML
    public void handleEntryButton() {
        try {
            // create variables from entry data
            LocalDate localDate = LocalDate.parse(dateTextField.getText());
            String description = descriptionTextField.getText();
            double amount = Double.parseDouble(amountTextField.getText());
            String category = categoryComboBox.getValue();
            String type = typeComboBox.getValue();
    
            // Create booleans for data tests
            boolean isDateValid = entryService.validateDate(localDate);
            boolean isAmountValid = entryService.validateAmount(amount);
    
            // Perform validation on the input data
            if (isDateValid && isAmountValid) {
                // Check if this is an update or a new entry
                if (isUpdate) {
                    // Update the transaction in the database
                    entryService.addTransaction(transactionId, localDate, description, amount, category, type);
                } else {
                    // Add a new transaction to the database
                    entryService.addTransaction(localDate, description, amount, category, type);
                }
                
                // refresh the observable list bound to the tableview
                transactionService.refreshTransactionList();

                // Close the Entry Form window
                Stage stage = (Stage) entryButton.getScene().getWindow();
                stage.close();
            
            // All potential errors are handled below
            } else {
                ErrorWindow.showErrorAlert("Please enter a valid date and amount in the proper format.");
            }
        } catch (DateTimeParseException e) {
            ErrorWindow.showErrorAlert("Please enter a valid date in the format YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            ErrorWindow.showErrorAlert("Please enter a valid number for the amount.");
        } catch (Exception e) {
            ErrorWindow.showErrorAlert("An unexpected error occurred. Please try again.");
        }
    }

    // Cancel Button controller code
    @FXML
    private Button cancelButton;

    @FXML
    public void handleCancelButton() {
        // Close the current window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // initialize the controller
    public void initialize() {
        // Initialize the ComboBoxes with values
        categoryComboBox.getItems().addAll("Food", "Transportation", "Entertainment", "Utilities", "Other");
        typeComboBox.getItems().addAll("Income", "Expense");

        // Set promt text for the TextFields
        dateTextField.setPromptText("YYYY-MM-DD");
        descriptionTextField.setPromptText("Text here");
        amountTextField.setPromptText("0000.00");

        // Set default values for the ComboBoxes
        categoryComboBox.setValue("Other");
        typeComboBox.setValue("Expense");
    }

}
