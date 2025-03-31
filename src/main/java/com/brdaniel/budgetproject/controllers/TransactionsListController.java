package com.brdaniel.budgetproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class TransactionsListController {
    // Transactions list controller code
    @FXML
    private ListView<String> transactionsListView;

    // Sort By controller code
    @FXML
    private ComboBox<String> sortByComboBox;

    // Filter By controller code
    @FXML
    private ComboBox<String> filterByComboBox;

    // Add Transaction controller code
    @FXML
    private Button addTransactionButton;

    // Update Transaction controller code
    @FXML
    private Button updateTransactionButton;

    // Remove Transaction controller code
    @FXML
    private Button removeTransactionButton;

    // Summary controller code
    @FXML
    private ListView<String> summaryListView;
}
