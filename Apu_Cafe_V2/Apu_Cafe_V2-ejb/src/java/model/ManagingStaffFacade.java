/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class ManagingStaffFacade extends AbstractFacade<ManagingStaff> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagingStaffFacade() {
        super(ManagingStaff.class);
    }
    
public ManagingStaff findManagingStaffById(Long id) {
    try {
        return em.find(ManagingStaff.class, id);
    } catch (NoResultException e) {
        System.out.println("Managing staff not found");
        return null;
    }
}

public ManagingStaff findManagingStaffByUserAccountId(Long userAccountId) {
    try {
        TypedQuery<ManagingStaff> query = em.createQuery(
            "SELECT m FROM ManagingStaff m WHERE m.userAccount.id = :userAccountId", ManagingStaff.class);
        query.setParameter("userAccountId", userAccountId);
        return query.getSingleResult();
    } catch (NoResultException e) {
        System.out.println("Managing staff not found for user_account_id: " + userAccountId);
        return null;
    }
}


   public void deleteManagingStaffById(Long itemId) {
        ManagingStaff mngStf = em.find(ManagingStaff.class, itemId); // Find the menu item by its ID
        if (mngStf != null) {
            em.remove(mngStf); // Remove the menu item if found
        }
    }
   
       public void updateManagingStaff(Long id, String email, String phoneNo, String fullName,
                                    String city, String addressLine1, String addressLine2,
                                    String gender, String race, int age) {
        ManagingStaff mngStf = em.find(ManagingStaff.class, id);
        if (mngStf != null) {
            mngStf.setEmail(email);
            mngStf.setPhoneNumber(phoneNo);
            mngStf.setFullName(fullName);
            mngStf.setCity(city);
            mngStf.setAddressLine1(addressLine1);
            mngStf.setAddressLine2(addressLine2);
            mngStf.setGender(gender);
            mngStf.setRace(race);
            mngStf.setAge(age);
            em.merge(mngStf); // Merge the updated object with the persistence context
        }
    }



    
}
