package com.brdaniel.budgetproject.controllers;

import java.time.LocalDate;

import com.brdaniel.budgetproject.services.EntryService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    @FXML
    public void handleEntryButton() {
        // create variables from entry data
        LocalDate localDate = LocalDate.parse(dateTextField.getText());
        String description = descriptionTextField.getText();
        double amount = Double.parseDouble(amountTextField.getText());
        String category = categoryComboBox.getValue();
        String type = typeComboBox.getValue();

        // Call entry service to enter data into database
        entryService.addTransaction(localDate, description, amount, category, type);
    }
    // Cancel Button controller code
    @FXML
    private Button cancelButton;

    // initialize the controller
    public void initialize() {
        // Initialize the ComboBoxes with values
        categoryComboBox.getItems().addAll("Food", "Transportation", "Entertainment", "Utilities", "Other");
        typeComboBox.getItems().addAll("Income", "Expense");
    }

}
