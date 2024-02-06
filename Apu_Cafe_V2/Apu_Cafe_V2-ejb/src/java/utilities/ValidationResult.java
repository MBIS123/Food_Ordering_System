/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */

public class ValidationResult {

    private boolean isValid;
    private List<String> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getErrors() {

        StringBuilder errorMessageBuilder = new StringBuilder("Validation failed!\\n");
        for (String errorMessage : errors) {
            errorMessageBuilder.append(errorMessage).append("\\n");
        }
        String errorMessageString = errorMessageBuilder.toString();
        return errorMessageString;
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
