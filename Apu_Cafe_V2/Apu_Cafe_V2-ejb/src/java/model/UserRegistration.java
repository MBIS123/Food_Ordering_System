/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.AbstractFacade;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import utilities.ValidationResult;

/**
 *
 * @author User
 */
public abstract class UserRegistration<T> {

    private static final Logger LOG = Logger.getLogger(UserRegistration.class.getName());

    // template design pattern
    protected abstract ValidationResult validateRegistrationDetail(T newUser, Map<String, String> customValidInfo);

    //template method
    public String createUser(AbstractFacade<T> entityFacade, T newUser, Map<String, String> customValidInfo) {

        if (validateRegistrationDetail(newUser, customValidInfo).isValid()) {

            System.out.println("validated succesfully");

            try {
                System.out.println(newUser.toString());
                entityFacade.create(newUser);
                System.out.println("create sucussfull la ");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ceate failed la ");
            }
            return null;
        } else {

            return validateRegistrationDetail(newUser, customValidInfo).getErrors();
        }
    }

}
