package com.company.config;

public class Main {

    public static void main(String[] args) {

        SystemConfig validConfig = new SystemConfig(8, 2500);
        ConfigValidator.validate(validConfig);

        SystemConfig invalidConfig = new SystemConfig(20, 2500);
        try {
            ConfigValidator.validate(invalidConfig);
        } catch (ConfigValidationException e) {
            System.out.println("VALIDATION FAILED: " + e.getMessage());
        }
    }
}

