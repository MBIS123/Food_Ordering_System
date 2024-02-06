/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class StallStaffPendingFacade extends AbstractFacade<StallStaffPending> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StallStaffPendingFacade() {
        super(StallStaffPending.class);
    }
    
    public boolean isPendingStaffExist(String username) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(s) FROM StallStaffPending s WHERE s.userName = :username AND s.status <> 'Rejected'", Long.class);
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count > 0;
    }
    
    
    
    
    
}
