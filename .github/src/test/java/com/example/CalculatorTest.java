package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.calculate(10, 5, "add"));
    }

    @Test
    void testSub() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.calculate(10, 5, "sub"));
    }
}
