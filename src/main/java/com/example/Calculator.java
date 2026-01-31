package com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        switch (op) {
            case "add":
                return add(a, b);
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;
            case "mod":
                return a % b;
            case "pow":
                return power(a, b);
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }
}
