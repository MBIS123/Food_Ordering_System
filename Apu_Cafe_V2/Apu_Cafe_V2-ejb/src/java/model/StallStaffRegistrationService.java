/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Stall;
import model.StallFacade;
import model.StallStaff;
import java.util.Map;
import javax.ejb.EJB;
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
public class StallStaffRegistrationService extends UserRegistration<StallStaff> {

    @EJB
    private StallFacade stallFacade;

    public boolean isStoreExist(String storeName) {

        Stall stlObj = stallFacade.findStallByStoreName(storeName);
        if (stlObj == null) {
            System.out.println("Validation failed: Store not exists");
            return false;
        } else {
            return true;
        }

    }

    protected ValidationResult validateRegistrationDetail(StallStaff newUser, Map<String, String> customValidInfo) {


        ValidationResult validationResult = new ValidationResult();

        String username = newUser.getUsername();
        String password = newUser.getPassword();

        String fullName = newUser.getFullName();
        String email = newUser.getEmail();

        String phoneNo = newUser.getPhoneNumber();
        String confirmPassword = customValidInfo.get("confirmPassword");
        String storeName = customValidInfo.get("storeName");

        if (!InputValidation.isValidString(username)) {
            System.out.println("Validation failed: Invalid username");
            validationResult.addError("Invalid username");
        }

        String pattern = "^(?i)ss\\d{6}$";

        if (!username.matches(pattern)) {
            validationResult.addError("Invalid stall staff username, ensure your username ");
        }

        // Validate Password
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
