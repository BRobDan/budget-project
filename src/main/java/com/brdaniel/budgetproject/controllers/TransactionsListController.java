package com.brdaniel.budgetproject.controllers;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionsListController {
    // Transactions list controller code
    @FXML
    private TableView<Transaction> transactionsTableView;

    // TableView Columns controller code
    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> descriptionColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, String> typeColumn;

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
    private Button summaryButton;
}
