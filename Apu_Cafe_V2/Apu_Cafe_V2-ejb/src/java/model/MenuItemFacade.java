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
public class MenuItemFacade extends AbstractFacade<MenuItem> {

    @PersistenceContext(unitName = "Apu_Cafe_V2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void deleteMenuById(Long itemId) {
        MenuItem menu = em.find(MenuItem.class, itemId); // Find the menu item by its ID
        if (menu != null) {
            em.remove(menu); // Remove the menu item if found
        }
    }

    // Edit menu item based on ID
    public void editMenu(Long itemId, String newName, String newCategory, double newPrice, String newImageUrl) {
        MenuItem menu = em.find(MenuItem.class, itemId); // Find the menu item by its ID
        if (menu != null) {
            // Update the menu item properties
            menu.setName(newName);
            System.out.println("newnameis" + newName);
            System.out.println("is is" + itemId);

            menu.setCategory(newCategory);
            menu.setPrice(newPrice);
            menu.setImageUrl(newImageUrl);

            // Use the abstract edit method to update the entity in the database
            edit(menu);
        } else {
            System.out.println("id is when failed " + itemId);

            System.out.println("cant find to edit");

        }
    }

    public List<MenuItem> findByStallId(Long stallId) {
        TypedQuery<MenuItem> query = em.createQuery(
                "SELECT m FROM MenuItem m WHERE m.stall.id = :stallId", MenuItem.class);
        query.setParameter("stallId", stallId);
        return query.getResultList();
    }

    public List<MenuItem> findByCategory(String category) {
        TypedQuery<MenuItem> query = em.createQuery(
                "SELECT m FROM MenuItem m WHERE m.category = :category", MenuItem.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    public MenuItemFacade() {
        super(MenuItem.class);
    }

}
