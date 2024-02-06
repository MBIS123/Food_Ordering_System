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
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Customer findCustomerByUserAccountId(Long userAccountId) {
        try {
            TypedQuery<Customer> query = em.createQuery(
                    "SELECT s FROM Customer s WHERE s.userAccount.id = :userAccountId", Customer.class);
            query.setParameter("userAccountId", userAccountId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Stall staff not found for user_account_id: " + userAccountId);
            return null;
        }
    }

    public Customer findCustomerById(Long id) {
        try {
            return em.find(Customer.class, id);
        } catch (NoResultException e) {
            System.out.println("Customer not found");
            return null;
        }
    }

    public void deleteCustomerById(Long itemId) {
        Customer cus = em.find(Customer.class, itemId); // Find the menu item by its ID
        if (cus != null) {
            em.remove(cus); // Remove the menu item if found
        }
    }

    public CustomerFacade() {
        super(Customer.class);
    }

}
