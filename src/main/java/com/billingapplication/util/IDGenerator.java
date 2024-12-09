package com.billingapplication.util;

import java.util.Random;

public class IDGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8; // Length of the generated ID

    // Method to generate a random invoice ID
    public static String generateInvoiceId() {
        return generateRandomString();
    }

    // Method to generate a random GSTIN
    public static String generateGstin() {
        return generateRandomString();
    }

    // Method to generate a random Bill Number
    public static String generateBillNum() {
        return generateRandomString();
    }

    // Private helper method to generate a random alphanumeric string
    private static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
