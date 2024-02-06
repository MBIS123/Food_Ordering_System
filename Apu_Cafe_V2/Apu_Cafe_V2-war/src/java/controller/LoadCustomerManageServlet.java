/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.MenuItem;
import model.MenuItemFacade;
import model.Order;
import model.OrderFacade;
import model.StallStaff;

/**
 *
 * @author User
 */
@WebServlet(name = "LoadCustomerManageServlet", urlPatterns = {"/LoadCustomerManageServlet"})
public class LoadCustomerManageServlet extends HttpServlet {

    @EJB
    private OrderFacade orderFacade;

    @EJB
    private MenuItemFacade menuItemFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = (String) request.getAttribute("action");
            System.out.println("action loadcusmangsef:" + action);
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("cusInfo");

            if ("backHome".equals(action)) {
                System.out.println("start loading menu");
                List<MenuItem> mainDishes = menuItemFacade.findByCategory("Main Dish");
                List<MenuItem> sideDishes = menuItemFacade.findByCategory("Side Dish");
                List<MenuItem> beverages = menuItemFacade.findByCategory("Beverage");
                List<MenuItem> allMenuItems = new ArrayList<>();
                allMenuItems.addAll(mainDishes);
                allMenuItems.addAll(sideDishes);
                allMenuItems.addAll(beverages);
                request.setAttribute("menuItems", allMenuItems);
                request.getRequestDispatcher("customerHome.jsp").include(request, response);
            } else if ("profile".equals(action)) {
                request.setAttribute("cusInfo", cus);
                request.getRequestDispatcher("customerProfile.jsp").forward(request, response);
            } else if ("viewOrder".equals(action)) {
                List<Order> mdOrder = orderFacade.findOrdersByCustomerId(cus.getId());
                request.setAttribute("madeOrder", mdOrder);
                request.getRequestDispatcher("customerViewOrder.jsp").forward(request, response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            System.out.println("action loadcusmangsef:" + action);
            HttpSession session = request.getSession();

            Customer cusInfo = (Customer) session.getAttribute("cusInfo");

            if ("backHome".equals(action)) {
                System.out.println("start loading menu");
                List<MenuItem> mainDishes = menuItemFacade.findByCategory("Main Dish");
                List<MenuItem> sideDishes = menuItemFacade.findByCategory("Side Dish");
                List<MenuItem> beverages = menuItemFacade.findByCategory("Beverage");
                List<MenuItem> allMenuItems = new ArrayList<>();
                allMenuItems.addAll(mainDishes);
                allMenuItems.addAll(sideDishes);
                allMenuItems.addAll(beverages);
                request.setAttribute("menuItems", allMenuItems);
                request.getRequestDispatcher("customerHome.jsp").include(request, response);

            } else if ("profile".equals(action)) {
                request.setAttribute("cusInfo", cusInfo);
                request.getRequestDispatcher("customerProfile.jsp").forward(request, response);

            } else if ("viewOrder".equals(action)) {
                List<Order> mdOrder = orderFacade.findOrdersByCustomerId(cusInfo.getId());
                request.setAttribute("madeOrder", mdOrder);
                request.getRequestDispatcher("customerViewOrder.jsp").forward(request, response);

            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
