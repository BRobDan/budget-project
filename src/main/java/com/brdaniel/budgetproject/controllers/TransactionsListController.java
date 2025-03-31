package com.brdaniel.budgetproject.controllers;

import java.io.IOException;

import com.brdaniel.budgetproject.models.Transaction;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TransactionsListController {
    // Transactions tableview controller code
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

    @FXML
    public void handleAddTransactionButton() {
        try {
            // Load the entry form FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EntryForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Create a new stage for the entry form
            Stage stage = new Stage();
            stage.setTitle("Add New Transaction");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update Transaction controller code
    @FXML
    private Button updateTransactionButton;

    // Remove Transaction controller code
    @FXML
    private Button removeTransactionButton;

    // Summary controller code
    @FXML
    private Button summaryButton;

    // Initialize the controller
    public void initialize() {
        // Initialize the transactions tableview and columns
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().localDateProperty().toString()));
        // Used a SimpleStringProperty to convert LocalDate to String for display in TableView
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        // debug message
        System.out.println("TransactionsListController initialized");
        
        // Initialize the sort by and filter by combo boxes
        sortByComboBox.getItems().addAll("Date", "Description", "Amount", "Category", "Type");
        filterByComboBox.getItems().addAll("All", "Income", "Expense");

        // Set default values for the combo boxes
        sortByComboBox.setValue("Date");
        filterByComboBox.setValue("All");
    }
}
