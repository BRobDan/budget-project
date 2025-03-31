package com.brdaniel.budgetproject.models;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

// Class for storing transaction data
public class Transaction {

	// Transaction variables with Getter methods
    @Getter
    private final int id;
    @Getter
    private final SimpleObjectProperty<LocalDate> date;
    @Getter
    private final SimpleStringProperty description;
    @Getter
    private final SimpleDoubleProperty amount;
    @Getter
    private final SimpleStringProperty category;
    @Getter
    private final SimpleStringProperty type;

    // Constructor
    public Transaction(int id, LocalDate date, String description, double amount, String category, String type) {
        this.id = id;
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleDoubleProperty(amount);
        this.category = new SimpleStringProperty(category);
        this.type = new SimpleStringProperty(type);
    }

    // Property methods for autobinding in TableView
    public SimpleObjectProperty<LocalDate> localDateProperty() {
        return date;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }
}
