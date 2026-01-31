package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void calculate_add_returnsSum() {
        assertEquals(7, calculator.calculate(3, 4, "add"));
    }

    @Test
    void calculate_sub_returnsDifference() {
        assertEquals(6, calculator.calculate(10, 4, "sub"));
    }

    @Test
    void calculate_mul_returnsProduct() {
        assertEquals(12, calculator.calculate(3, 4, "mul"));
    }

    @Test
    void calculate_div_returnsQuotient() {
        assertEquals(5, calculator.calculate(10, 2, "div"));
    }

    @Test
    void calculate_div_byZero_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(10, 0, "div")
        );
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    void calculate_mod_returnsRemainder() {
        assertEquals(1, calculator.calculate(10, 3, "mod"));
    }

    @Test
    void calculate_pow_returnsPower() {
        assertEquals(81, calculator.calculate(3, 4, "pow"));
    }

    @Test
    void calculate_unknownOperation_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(1, 2, "somethingElse")
        );
        assertEquals("Unknown operation", ex.getMessage());
    }
}