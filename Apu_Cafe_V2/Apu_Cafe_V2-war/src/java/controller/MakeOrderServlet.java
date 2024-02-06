/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author User
 */
@WebServlet(name = "MakeOrderServlet", urlPatterns = {"/MakeOrderServlet"})
public class MakeOrderServlet extends HttpServlet {

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

            System.out.println("Enter makeOrderServlet");

            String itemId = request.getParameter("itemId");

            MenuItem menuItem = menuItemFacade.find(Long.parseLong(itemId));
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("cusInfo");
            String status = "Unpaid";
            String feedback = "";
            int rating = 0;

            Order createOrder = new Order(cus, menuItem, feedback, status, rating);
            out.println("<script type='text/javascript'>alert('Your order was made!!');history.back();</script>");
            request.setAttribute("action", "backHome");
            request.getRequestDispatcher("LoadCustomerManageServlet").include(request, response);

            try {
                orderFacade.create(createOrder);
            } catch (Exception e) {
                e.printStackTrace();

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
