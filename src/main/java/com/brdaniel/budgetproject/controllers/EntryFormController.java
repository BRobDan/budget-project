package com.brdaniel.budgetproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.brdaniel.budgetproject.services.EntryService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntryFormController {
    // initialize the entry service
    private final EntryService entryService = new EntryService();

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

    // Method for showing error alerts for invalid input
    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
                // Call entry service to enter data into database
                entryService.addTransaction(localDate, description, amount, category, type);
                // Close the Entry Form window
                Stage stage = (Stage) entryButton.getScene().getWindow();
                stage.close();
            
            // All potential errors are handled below
            } else {
                showErrorAlert("Please enter a valid date and amount in the proper format.");
            }
        } catch (DateTimeParseException e) {
            showErrorAlert("Please enter a valid date in the format YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            showErrorAlert("Please enter a valid number for the amount.");
        } catch (Exception e) {
            showErrorAlert("An unexpected error occurred. Please try again.");
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
