/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Customer;
import model.ManagingStaff;
import model.ManagingStaffFacade;
import model.Stall;
import model.StallStaff;
import model.UserAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerRegistrationService;
import model.ManagingStaffRegistrationService;
import model.StallStaffRegistrationService;
import model.UserRegistration;

/**
 *
 * @author User
 */
@WebServlet(name = "AddManagingStaffServlet", urlPatterns = {"/AddManagingStaffServlet"})
public class AddManagingStaffServlet extends HttpServlet {

    @EJB
    private ManagingStaffFacade managingStaffFacade;

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

        System.out.println("First get in processRequest");
        try (PrintWriter out = response.getWriter()) {
            try {

                Map<String, String> extraRegisterInfo = new HashMap<>();

                String errorsMessage;

                String username = request.getParameter("username");

                String password = request.getParameter("password");

                String confirmPassword = request.getParameter("confirmPassword");

                String email = request.getParameter("email");

                String phoneNo = request.getParameter("phoneNumber");

                String fullName = request.getParameter("displayname");

                String userType = request.getParameter("userType");

                String city = request.getParameter("city");

                String addressLine1 = request.getParameter("addressLine1");

                String addressLine2 = request.getParameter("addressLine2");

                String gender = request.getParameter("gender");

                String race = request.getParameter("race");

                int age = Integer.parseInt(request.getParameter("age"));

                extraRegisterInfo.put("confirmPassword", confirmPassword);
                UserRegistration<ManagingStaff> userRegistration = new ManagingStaffRegistrationService();
                if (!userAccountFacade.isUsernameExist(username)) {
                    System.out.println("the password is " + password);
                    errorsMessage = userRegistration.createUser(managingStaffFacade, new ManagingStaff(username, password, fullName, email, phoneNo, userType, city, addressLine1, addressLine2, gender, race, age),
                            extraRegisterInfo);
                    if (errorsMessage == null) {
                        out.println("<script type='text/javascript'>alert('Registration successfully! Enjoy APU Cafeteria');</script>");
                        request.setAttribute("userType","managingStaff");
                        request.getRequestDispatcher("LoadUserInfoServlet").include(request, response);
                        System.out.println("registration completed" + username);
                    } else {
                        System.out.println(" failed registration");
                        out.println("<script type='text/javascript'>alert('" + errorsMessage + "');history.back();</script>");
                    }
                } else {
                    out.println("<script type='text/javascript'>alert('Username is used !!');history.back();</script>");
                }

            } catch (Exception e) {
                request.getRequestDispatcher("register.jsp").include(request, response);
                out.println("<br><br><br>Some Error happened! " + request.getParameter("userType"));
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
