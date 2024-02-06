/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class OrderFacade extends AbstractFacade<Order> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Order> findOrdersByStallId(Long stallId) {
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o WHERE o.menuItem.stall.id = :stallId ORDER BY o.id DESC", Order.class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Order> findPaidOrdersByStallId(Long stallId) {
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o WHERE o.menuItem.stall.id = :stallId AND o.status = :status ORDER BY o.id DESC", Order.class);
        query.setParameter("stallId", stallId);
        query.setParameter("status", "Paid");
        return query.getResultList();
    }

    public long countPaidOrdersByStallId(Long stallId) {
        return em.createQuery("SELECT COUNT(o) FROM Order o WHERE o.menuItem.stall.id = :stallId AND o.status = 'Paid'", Long.class)
                .setParameter("stallId", stallId)
                .getSingleResult();
    }

    public List<Object[]> findMenuItemCountsByStallIdAndPaidStatus(Long stallId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT mi.name, COUNT(mi) FROM Order o JOIN o.menuItem mi WHERE mi.stall.id = :stallId AND o.status = 'Paid' GROUP BY mi.name",
                Object[].class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Object[]> countDistinctCustomersByAgeGroupsAndStallId(Long stallId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT "
                + "CASE "
                + "   WHEN c.age BETWEEN 18 AND 25 THEN '18-25' "
                + "   WHEN c.age BETWEEN 26 AND 35 THEN '26-35' "
                + "   WHEN c.age BETWEEN 36 AND 40 THEN '36-40' "
                + "   ELSE '40+' "
                + "END AS ageGroup, COUNT(DISTINCT c.id) "
                + "FROM Order o JOIN o.customer c JOIN o.menuItem mi "
                + "WHERE mi.stall.id = :stallId AND o.status = 'Paid' "
                + "GROUP BY ageGroup",
                Object[].class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Object[]> countDistinctCustomersByGenderAndStallId(Long stallId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c.gender, COUNT(DISTINCT c.id) FROM Order o "
                + "JOIN o.customer c JOIN o.menuItem mi "
                + "WHERE mi.stall.id = :stallId AND o.status = 'Paid' "
                + "GROUP BY c.gender",
                Object[].class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Object[]> countDistinctCustomersByRaceAndStallId(Long stallId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c.race, COUNT(DISTINCT c.id) FROM Order o "
                + "JOIN o.customer c JOIN o.menuItem mi "
                + "WHERE mi.stall.id = :stallId AND o.status = 'Paid' "
                + "GROUP BY c.race",
                Object[].class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Object[]> findAverageRatingsByMenuItemAndStallId(Long stallId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT mi.name, AVG(o.rating) FROM Order o "
                + "JOIN o.menuItem mi "
                + "WHERE mi.stall.id = :stallId AND o.status = 'Paid' "
                + "GROUP BY mi.name",
                Object[].class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<Order> findOrdersByCustomerId(Long customerId) {
        TypedQuery<Order> query = em.createQuery(
                "SELECT o FROM Order o WHERE o.customer.id = :customerId ORDER BY o.id DESC", Order.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public List<Object[]> findTop5StoresByPaidOrders() {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT s.storeName, COUNT(o) FROM Order o JOIN o.menuItem mi JOIN mi.stall s WHERE o.status = 'Paid' GROUP BY s.storeName ORDER BY COUNT(o) DESC", Object[].class);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public OrderFacade() {
        super(Order.class);
    }

}
