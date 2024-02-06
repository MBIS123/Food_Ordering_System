/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
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
public class StallStaffFacade extends AbstractFacade<StallStaff> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void deleteStallStaffById(Long itemId) {
        StallStaff menu = em.find(StallStaff.class, itemId); // Find the menu item by its ID
        if (menu != null) {
            em.remove(menu); // Remove the menu item if found
        }
    }
    
    public StallStaff findStallStaffByUserAccountId(Long userAccountId) {
    try {
        TypedQuery<StallStaff> query = em.createQuery(
            "SELECT s FROM StallStaff s WHERE s.userAccount.id = :userAccountId", StallStaff.class);
        query.setParameter("userAccountId", userAccountId);
        return query.getSingleResult();
    } catch (NoResultException e) {
        System.out.println("Stall staff not found for user_account_id: " + userAccountId);
        return null;
    }
}
  public List<Object[]> countStaffByGender() {
        return em.createQuery(
                "SELECT ss.gender, COUNT(ss) FROM StallStaff ss GROUP BY ss.gender", Object[].class)
                .getResultList();
    }

    public StallStaff findStallStaffById(Long id) {
        try {
            return em.find(StallStaff.class, id);
        } catch (NoResultException e) {
            System.out.println("Stall staff not found");
            return null;
        }
    }

    public StallStaffFacade() {
        super(StallStaff.class);
    }

}
