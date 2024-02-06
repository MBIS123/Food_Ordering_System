/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CustomerFacade;
import model.UserAccountFacade;
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
import model.UserAccount;

/**
 *
 * @author User
 */
@WebServlet(name = "ManageCustomerServlet", urlPatterns = {"/ManageCustomerServlet"})
public class ManageCustomerServlet extends HttpServlet {



    @EJB
    private OrderFacade orderFacade;

    @EJB
    private MenuItemFacade menuItemFacade;

    @EJB
    private UserAccountFacade userAccountFacade;

    @EJB
    private CustomerFacade customerFacade;

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

            System.out.println("Entered manageManagingStaffServlet");
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("cusInfo");

            String action = request.getParameter("action");

            if ("makeOrder".equals(action)) {
                System.out.println("Enter makeOrderServlet");
                String itemId = request.getParameter("itemId");
                MenuItem menuItem = menuItemFacade.find(Long.parseLong(itemId));
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

            } else if ("editFeedback".equals(action)) {
                Long orderId = Long.parseLong(request.getParameter("orderId"));

                String feedback = request.getParameter("feedback");
                Order orderFeedback = orderFacade.find(orderId);
                orderFeedback.setFeedback(feedback);
                orderFacade.edit(orderFeedback);
                request.setAttribute("action", "viewOrder");
                request.getRequestDispatcher("LoadCustomerManageServlet").forward(request, response);

            } else if ("editRating".equals(action)) {
                Long orderId = Long.parseLong(request.getParameter("orderId"));

                int rating = Integer.parseInt(request.getParameter("rating"));
                System.out.println("ratingf:" + rating);
                Order orderRating = orderFacade.find(orderId);
                orderRating.setRating(rating);
                orderFacade.edit(orderRating);
                request.setAttribute("action", "viewOrder");
                request.getRequestDispatcher("LoadCustomerManageServlet").forward(request, response);
            } else if ("editProfile".equals(action)) {

                String fullName = request.getParameter("fullName");
                int age = Integer.parseInt(request.getParameter("age"));
                String password = request.getParameter("password");
                if (password != null && password.length() < 4) {
                    out.println("<script type='text/javascript'>alert(' Password must be more than length of 3!');history.back();</script>");
                    return;
                }
                String city = request.getParameter("city");
                String addressLine1 = request.getParameter("addressLine1");
                String addressLine2 = request.getParameter("addressLine2");
                String phoneNumber = request.getParameter("phoneNumber");
                String email = request.getParameter("email");

                try {
                    UserAccount user = userAccountFacade.find(cus.getUserAccountID());

                    cus.setFullName(fullName);
                    cus.setAge(age);
                    user.setPassword(password);
                    cus.setCity(city);
                    cus.setAddressLine1(addressLine1);
                    cus.setAddressLine2(addressLine2);
                    cus.setPhoneNumber(phoneNumber);
                    cus.setEmail(email);

                    customerFacade.edit(cus);
                    userAccountFacade.edit(user);
                    request.setAttribute("successMessage", "Profile updated successfully!");
                    request.setAttribute("action", "profile");
                    request.getRequestDispatcher("LoadCustomerManageServlet").forward(request, response);
                } catch (Exception e) {
                    out.println("<script type='text/javascript'>alert('Update Profile failed!!');history.back();</script>");
                    e.printStackTrace();
                }

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
