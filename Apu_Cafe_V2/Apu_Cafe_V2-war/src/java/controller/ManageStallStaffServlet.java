/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.StallStaffFacade;
import model.UserAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.OrderFacade;
import model.StallFacade;
import model.StallStaff;
import model.StallStaffPendingFacade;
import model.UserAccount;

/**
 *
 * @author User
 */
@WebServlet(name = "ManageStallStaffServlet", urlPatterns = {"/ManageStallStaffServlet"})
public class ManageStallStaffServlet extends HttpServlet {

    @EJB
    private OrderFacade orderFacade;

    @EJB
    private StallFacade stallFacade;

    @EJB
    private StallStaffPendingFacade stallStaffPendingFacade;

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private UserAccountFacade userAccountFacade;

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

            HttpSession session = request.getSession();
            StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");

            System.out.println("Entered manageStallStaffServlet");

            String action = request.getParameter("action");
            System.out.println("action:" + action);

            if ("collectPayment".equals(action)) {

                Long orderId = Long.parseLong(request.getParameter("orderId"));
                try {
                    Order order = orderFacade.find(orderId);
                    order.setOrderTimestamp(LocalDateTime.now());
                    order.setStatus("Paid");
                    orderFacade.edit(order);
                    request.setAttribute("action", "backHome");
                    request.getRequestDispatcher("LoadStallStaffManageServlet").forward(request, response);
                } catch (Exception e) {
                    out.println("<script type='text/javascript'>alert('Collect Payment Failed !!');history.back();</script>");
                    e.printStackTrace();
                }
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
                    UserAccount user = userAccountFacade.find(staffInfo.getUserAccountID());
                    
                    staffInfo.setFullName(fullName);
                    staffInfo.setAge(age);
                    user.setPassword(password);
                    staffInfo.setCity(city);
                    staffInfo.setAddressLine1(addressLine1);
                    staffInfo.setAddressLine2(addressLine2);
                    staffInfo.setPhoneNumber(phoneNumber);
                    staffInfo.setEmail(email);

                    stallStaffFacade.edit(staffInfo);
                    userAccountFacade.edit(user);
                    request.setAttribute("successMessage", "Profile updated successfully!");
                    request.setAttribute("action", "profile");
                    request.getRequestDispatcher("LoadStallStaffManageServlet").forward(request, response);
                } catch (Exception e) {
                    out.println("<script type='text/javascript'>alert('Update Profile failed!!');history.back();</script>");
                    e.printStackTrace();
                }
            }else if("generateReceipt".equals(action)){
                Long orderId =  Long.parseLong(request.getParameter("orderId"));
                Order order = orderFacade.find(orderId);
                request.setAttribute("orderDetails", order);
                request.getRequestDispatcher("receipt.jsp").forward(request, response);

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
