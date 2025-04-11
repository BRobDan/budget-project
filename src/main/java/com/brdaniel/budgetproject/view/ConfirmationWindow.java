package com.brdaniel.budgetproject.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// Reusable class for showing confirmation windows
public class ConfirmationWindow {
    
    // Method for popping up confirmation windows
    public static boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Pops up alert and waits for user selection
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // returns true if user selects OK
        if (result == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
