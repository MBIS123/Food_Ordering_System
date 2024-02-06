/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class UserAccountFacade extends AbstractFacade<UserAccount> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void deleteUserAccountById(Long itemId) {
        UserAccount userAccount = em.find(UserAccount.class, itemId); // Find the menu item by its ID
        if (userAccount != null) {
            em.remove(userAccount); // Remove the menu item if found
        }
    }

    public boolean isUsernameExist(String username) {
        EntityManager em = getEntityManager();
        try {
            UserAccount account = em.createQuery("SELECT ua FROM UserAccount ua WHERE ua.username = :username", UserAccount.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return account != null;
        } catch (NoResultException e) {
            System.out.println("No user found with username: " + username);
            return false;
        }
        // Removed em.close();
    }

    public UserAccount findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM UserAccount u WHERE u.username = :username", UserAccount.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // User not found
        } catch (Exception e) {
            // Handle or log other exceptions
            return null;
        }
    }

    public Map<String, Long> countUsersByRole() {
        Map<String, Long> counts = new HashMap<>();
        String[] roles = {"customer", "managingStaff", "stallStaff"};

        for (String role : roles) {
            Long count = em.createQuery("SELECT COUNT(ua) FROM UserAccount ua WHERE ua.role = :role", Long.class)
                    .setParameter("role", role)
                    .getSingleResult();
            counts.put(role, count);
        }

        return counts;
    }

    public void updatePassword(Long userId, String newPassword) {
        UserAccount userAccount = em.find(UserAccount.class, userId); // Find the user account by its ID
        if (userAccount != null) {
            userAccount.setPassword(newPassword); // Set the new password
            em.merge(userAccount); // Merge the updated entity with the persistence context
        } else {
            // Optionally handle the case where the user account was not found
            System.out.println("User account not found for ID: " + userId);
        }
    }

    public UserAccountFacade() {
        super(UserAccount.class);
    }

}
