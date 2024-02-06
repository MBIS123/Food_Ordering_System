/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.MenuItem;
import model.MenuItemFacade;
import java.util.Map;
import javax.ejb.Stateless;
import utilities.InputValidation;

/**
 *
 * @author User
 */
@Stateless
public class ManageMenu {

    private MenuItem item;

    private boolean validateItemInfo() {

        boolean pass = true;

        if (!InputValidation.isValidString(item.getName())) {

            System.out.println("Validation failed: Invalid name");
            return false;
        }
        if (!InputValidation.isValidString(item.getCategory())) {
            System.out.println("Validation failed: Invalid category");
            return false;
        }

        return pass;

    }

    //template method
    public boolean addItem(MenuItemFacade entityFacade, MenuItem newItem) {

        this.item = newItem;

        if (validateItemInfo()) {

            System.out.println("validated succesfully");

            try {
                System.out.println(newItem.toString());
                entityFacade.create(newItem);
                System.out.println("create sucussfull la ");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("create failed la ");

                return false;
            }
            return true;

        } else {

            return false;
        }
    }
    
     

      
    

}
