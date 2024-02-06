/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Customer;
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
public class CustomerRegistrationService extends UserRegistration<Customer> {

    @Override
    protected ValidationResult validateRegistrationDetail(Customer newUser, Map<String, String> customValidInfo) {

        ValidationResult validationResult = new ValidationResult();

        System.out.println("enter validation");

        String username = newUser.getUsername();
        String password = newUser.getPassword();

        String fullName = newUser.getFullName();
        String email = newUser.getEmail();

        String phoneNo = newUser.getPhoneNumber();
        String confirmPassword = customValidInfo.get("confirmPassword");

        if (!InputValidation.isValidString(username)) {
            System.out.println("Validation failed: Invalid username");
            validationResult.addError("Invalid username");
        }


           if (!InputValidation.isPasswordValid(password)) {
            System.out.println("Validation failed: Invalid password");
            validationResult.addError("Invalid password that less than length of 4");
        }

        // Validate Full Name
        if (!InputValidation.isValidString(fullName)) {
            System.out.println("Validation failed: Invalid full name");
            validationResult.addError("Invalid full name");

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

        // Validate Confirm Password
        if (!InputValidation.isValidString(confirmPassword)) {
            System.out.println("Validation failed: Confirm password is invalid");
            validationResult.addError("Invalid password");
            System.out.println("the confirm pw is " + confirmPassword);

        }
        // Check Passwords Match
        if (!password.equals(confirmPassword)) {
            System.out.println("Validation failed: Passwords do not match");
            validationResult.addError("Confirm password is invalid");

        }
        System.out.println("before usernameExist");

        if (validationResult.hasErrors()) {
            validationResult.setValid(false);
        } else {
            validationResult.setValid(true);
        }

        return validationResult;

    }

}
