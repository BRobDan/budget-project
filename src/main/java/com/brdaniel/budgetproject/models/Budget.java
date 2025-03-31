package com.brdaniel.budgetproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok annotations
@Getter
@Setter
@ToString
@AllArgsConstructor

// This class represents the overall budget of the user
public class Budget {
    private double totalIncome;
    private double totalExpenses;
    private double netBalance;
    }