package com.brdaniel.budgetproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the Transactions List view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TransactionsList.fxml"));
            Parent root = loader.load();    // cast it to the Parent node for the JavaFX graph structure
            Scene scene = new Scene(root);  // use the Parent node to create a Scene

            // Set the scene to the primary stage and name it
            primaryStage.setTitle("Budget Tracker");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // This will help identify the actual exception
        }
    }

    // Launch the application
    public static void main(String[] args)
    {
        launch(args);
    }
}
