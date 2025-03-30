package com.brdaniel.budgetproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok annotations
@Getter
@Setter
@ToString

// This class represents a single transaction by the user
public class Transaction {
	private int id;
	private String description;
	private double amount;
	private String LocalDate;
	private String category;
	private String type;
}