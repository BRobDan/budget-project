package com.brdaniel.budgetproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EntryFormController {
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
}
