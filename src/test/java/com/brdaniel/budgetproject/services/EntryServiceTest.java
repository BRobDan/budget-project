package com.brdaniel.budgetproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;

// Test class for the EntryService
// I'm only testing the logic in the service since the CRUD operations were already
// tested in the DatabaseServiceTest class and because I'm only using JUnit
// and not mocking GUI components
public class EntryServiceTest {

    private EntryService entryService;

    // Setup method for the EntryService before each test
    @BeforeEach
    public void setUp() {
        entryService = new EntryService();
    }

    // Testing validateDate with a valid date
    @Test
    public void testValidateDate_ValidDate() {
        LocalDate validDate = LocalDate.now();
        assertTrue(entryService.validateDate(validDate));
    }

    // Testing validateDate with an invalid date
    @Test
    public void testValidateDate_InvalidDate() {
        LocalDate invalidDate = LocalDate.now().plusDays(6);
        assertFalse(entryService.validateDate(invalidDate));
    }

    // Testing validateAmount with a valid amount
    @Test
    public void testValidateAmount_ValidAmount() {
        double validAmount = 100.0;
        assertTrue(entryService.validateAmount(validAmount));
    }

    // Testing validateAmount with an invalid amount
    @Test
    public void testValidateAmount_InvalidAmount() {
        double invalidAmount = -50.0;
        assertFalse(entryService.validateAmount(invalidAmount));
    }
}
