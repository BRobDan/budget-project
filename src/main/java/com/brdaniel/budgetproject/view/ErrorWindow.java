package com.brdaniel.budgetproject.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// reusable class for showing error windows
public class ErrorWindow {
    
    // Method for showing error alerts for invalid input
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
