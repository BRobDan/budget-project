package com.brdaniel.budgetproject.controllers;

import com.brdaniel.budgetproject.services.SummaryService;
import com.brdaniel.budgetproject.services.TransactionService;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class SummaryPageController {
    // Initialize summary service
    private final SummaryService summaryService = new SummaryService();

    // Initialize transaction service to receive the lists of transactions
    private TransactionService transactionService = new TransactionService();

    // Method to pass the TransactionService to the SummaryService
    // This is used so the ObservableList is available to read from
    public void passTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Income label controller code
    @FXML
    private Label totalIncomeLabel;

    // Expenses label controller code
    @FXML
    private Label totalExpensesLabel;

    // Balance label controller code
    @FXML
    private Label netBalanceLabel;

    // Pie Chart controller code
    @FXML
    private PieChart expensesPieChart;

    // Initialize the controller
    public void initialize() {
        // Set the labels and pie chart with the data from the transaction service
        totalIncomeLabel.setText(String.valueOf(summaryService.getTotalIncome(transactionService.getTransactionsList())));
        totalExpensesLabel.setText(String.valueOf(summaryService.getTotalExpenses(transactionService.getTransactionsList())));
        netBalanceLabel.setText(String.valueOf(summaryService.getNetBalance(transactionService.getTransactionsList())));

        // Set the pie chart data from the pie chart data from the summary service
        expensesPieChart.setData(summaryService.getPieChartData(transactionService.getTransactionsList()));

        // Set the pie chart title
        expensesPieChart.setTitle("Expenses by Category");
    }

    // Getters for the unit tests
    protected TransactionService getTransactionService() {
        return transactionService;
    }

    protected SummaryService getSummaryService() {
        return summaryService;
    }
}
