package com.brdaniel.budgetproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class SummaryPageController {
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

}
