/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class InputValidation {

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    // Validate an integer
    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate a string (e.g., not empty)
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate a phone number (10 to 11 digits)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^\\d{10,11}$";
        return Pattern.matches(phoneNumberRegex, phoneNumber);
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() >= 4;
    }

}
