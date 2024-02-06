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
import javax.persistence.Tuple;

/**
 *
 * @author User
 */
@Stateless
public class StallFacade extends AbstractFacade<Stall> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StallFacade() {
        super(Stall.class);
    }

    public List<Object[]> groupStallsByTypeAndCount() {
        return getEntityManager().createQuery(
                "SELECT s.storeType, COUNT(s) FROM Stall s GROUP BY s.storeType", Object[].class)
                .getResultList();
    }

    public List<Object[]> getStallNamesAndStaffCount() {
        return em.createQuery(
                "SELECT s.storeName, COUNT(st) FROM Stall s JOIN s.staffList st GROUP BY s.storeName", Object[].class)
                .getResultList();
    }

    public Stall findStallByStoreName(String storeName) {

        System.out.println("storeName:" + storeName);

        try {
            System.out.println("start finding la===============");

            return getEntityManager().createQuery(
                    "SELECT s FROM Stall s WHERE s.storeName = :storeName", Stall.class)
                    .setParameter("storeName", storeName)
                    .getSingleResult();

        } catch (NoResultException e) {
            System.out.println("not found la");
            return null; // Stall not found
        }
    }
    
    

}
