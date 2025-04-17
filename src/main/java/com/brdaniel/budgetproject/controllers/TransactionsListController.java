package com.brdaniel.budgetproject.controllers;

import java.io.IOException;

import com.brdaniel.budgetproject.models.Transaction;
import com.brdaniel.budgetproject.services.TransactionService;
import com.brdaniel.budgetproject.view.ConfirmationWindow;
import com.brdaniel.budgetproject.view.ErrorWindow;

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
    // Initialize transaction service
    private final TransactionService transactionService = new TransactionService();

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

    @FXML
    private void handleSortByComboBox() {
        // Gets the selected value from the sort combo box and passes it to the transaction service for sorting
        transactionService.setCategorySort(sortByComboBox.getValue());
    }

    // Filter By controller code
    @FXML
    private ComboBox<String> filterByComboBox;

    @FXML
    public void handleFilterByComboBox() {
        // Gets the selected value from the filter combo box and sets it as the filter value for the filtered list
        transactionService.setCategoryFilter(filterByComboBox.getValue());
    }

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

            // pass transactionsService to the EntryFormController
            EntryFormController entryFormController = loader.getController();
            entryFormController.passTransactionService(transactionService);

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

    @FXML
    public void handleUpdateTransactionButton() {
        try {
            // Load the entry form FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EntryForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // pass transactionsService to the EntryFormController
            EntryFormController entryFormController = loader.getController();
            entryFormController.passTransactionService(transactionService);

            // pass selected transaction to the EntryFormController
            Transaction selectedTransaction = transactionsTableView.getSelectionModel().getSelectedItem();
            if (selectedTransaction == null) {
                ErrorWindow.showErrorAlert("Please select a transaction to update.");
                return;
            }
            entryFormController.passTransaction(selectedTransaction);

            // Create a new stage for the entry form
            Stage stage = new Stage();
            stage.setTitle("Update Transaction");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Remove Transaction controller code
    @FXML
    private Button removeTransactionButton;

    @FXML
    public void handleRemoveTransactionButton() {
        // Save the selected item from the tableview to a variable
        Transaction selectedItem = transactionsTableView.getSelectionModel().getSelectedItem();
        
        // Check if an item is seletected
        if (selectedItem == null) {
            // show an error message if no item is selected
            ErrorWindow.showErrorAlert("Please select a transaction to remove.");
            return;
        }

        // Confirmation alert asking the user if they want to remove the selected transaction
        if (!ConfirmationWindow.showConfirmationAlert("Remove Transaction", "Do you want to remove the selected transaction?")) {
            return; // return if the user cancels the action
        }

        // Remove the selected item from the tableview and database
        transactionService.removeTransaction(selectedItem);

        // Refresh the tableview with updated data
        transactionService.refreshTransactionList();
    }

    // Summary controller code
    @FXML
    private Button summaryButton;

    @FXML
    public void handleSummaryButton() {
        try {
            // Load the summary FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SummaryPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Initialize the summary controller and pass the transaction service to it
            SummaryPageController summaryController = loader.getController();
            summaryController.passTransactionService(transactionService);

            // Create a new stage for the summary
            Stage stage = new Stage();
            stage.setTitle("Summary");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize the controller
    public void initialize() {
        // Initialize the transactions tableview and columns
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().localDateProperty().get().toString()));
        // Used a SimpleStringProperty to convert LocalDate to String for display in TableView
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        // Set and bind items into the tableview
        transactionsTableView.setItems(transactionService.getAllTransactions());

        // Initialize the sort by and filter by combo boxes
        sortByComboBox.getItems().addAll("Date", "Amount");
        filterByComboBox.getItems().addAll("All", "Food", "Transportation", "Entertainment", 
            "Utilities", "Other",  "Income", "Expense");

        // Set default values for the combo boxes
        sortByComboBox.setValue("Date");
        filterByComboBox.setValue("All");

        // Perform the initial sorting by date
        transactionService.setCategorySort(sortByComboBox.getValue());

        // Perform the initial filtering by all transactions
        // This is redundant, but it's useful if the default value was ever set to something other than "All"
        transactionService.setCategoryFilter(filterByComboBox.getValue());
    }
}
