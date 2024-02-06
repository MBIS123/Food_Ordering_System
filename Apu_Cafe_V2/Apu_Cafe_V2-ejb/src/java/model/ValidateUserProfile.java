/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.StallStaff;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import utilities.InputValidation;
import utilities.ValidationResult;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class ValidateUserProfile {

    public ValidationResult validateRegistrationDetail(Map<String, String> customValidInfo) {

        ValidationResult validationResult = new ValidationResult();

        String password = customValidInfo.get("password");
        String fullName = customValidInfo.get("fullName");
        String email = customValidInfo.get("email");
        String phoneNo = customValidInfo.get("phoneNo");
        String confirmPassword = customValidInfo.get("confirmPassword");

        if (!InputValidation.isPasswordValid(password)) {
            System.out.println("Validation failed: Invalid password");
            validationResult.addError("Invalid password that less than length of 4");
        }
        // Validate Email
        if (!InputValidation.isValidEmail(email)) {
            System.out.println("Validation failed: Invalid email");
            validationResult.addError("Invalid email");
        }
        // Validate Phone Number
        if (!InputValidation.isValidPhoneNumber(phoneNo)) {
            System.out.println("Validation failed: Invalid phone number");
            validationResult.addError("Invalid phone number");
        }
        // Check Passwords Match
        if (!password.equals(confirmPassword)) {
            System.out.println("Validation failed: Passwords do not match");
            validationResult.addError("Confirm password is not equal to password");
        }
        if (validationResult.hasErrors()) {
            validationResult.setValid(false);
        } else {
            validationResult.setValid(true);
        }
        return validationResult;

    }

}
