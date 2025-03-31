package com.brdaniel.budgetproject.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok annotations
@Getter
@Setter
@ToString
@AllArgsConstructor

// This class represents a single transaction by the user
public class Transaction {
	private int id;
	private LocalDate localDate;
	private String description;
	private double amount;
	private String category;
	private String type;
}