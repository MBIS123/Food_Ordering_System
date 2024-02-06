/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ManagingStaffFacade;
import model.UserAccount;
import model.UserAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.CustomerFacade;
import model.ManagingStaff;
import model.StallStaff;
import model.StallStaffFacade;
import model.ValidateUserProfile;

/**
 *
 * @author User
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

    @EJB
    private ManagingStaffFacade managingStaffFacade1;

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private ValidateUserProfile validateUserProfile;

    @EJB
    private UserAccountFacade userAccountFacade;

    @EJB
    private ManagingStaffFacade managingStaffFacade;

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

            System.out.println("Entered UpdateProfileServlet  ");
            String userType = request.getParameter("userType");

            if ("customer".equals(userType)) {

                Long cusId = Long.parseLong(request.getParameter("cusId"));
                Customer cus = customerFacade.find(cusId);

                String fullName = request.getParameter("fullName");
                int age = Integer.parseInt(request.getParameter("age"));
                String password = request.getParameter("password");
                if (password != null && password.length() < 4) {
                    out.println("<script type='text/javascript'>alert(' Password must more than length of 3!');history.back();</script>");
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
                    request.setAttribute("userType", "customer");
                    request.getRequestDispatcher("LoadUserInfoServlet").forward(request, response);
                } catch (Exception e) {
                    out.println("<script type='text/javascript'>alert('Update Profile failed!!');history.back();</script>");
                    e.printStackTrace();
                }

            } else if ("stallStaff".equals(userType)) {

                Long stallStffId = Long.parseLong(request.getParameter("stallStffId"));
                StallStaff stallStaff = stallStaffFacade.find(stallStffId);

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
                    UserAccount user = userAccountFacade.find(stallStaff.getUserAccountID());

                    stallStaff.setFullName(fullName);
                    stallStaff.setAge(age);
                    user.setPassword(password);
                    stallStaff.setCity(city);
                    stallStaff.setAddressLine1(addressLine1);
                    stallStaff.setAddressLine2(addressLine2);
                    stallStaff.setPhoneNumber(phoneNumber);
                    stallStaff.setEmail(email);

                    stallStaffFacade.edit(stallStaff);
                    userAccountFacade.edit(user);
                    request.setAttribute("userType", "stallStaff");
                    request.getRequestDispatcher("LoadUserInfoServlet").forward(request, response);
                } catch (Exception e) {
                    out.println("<script type='text/javascript'>alert('Update Profile failed!!');history.back();</script>");
                    e.printStackTrace();
                }

            } else if ("managingStaff".equals(userType)) {

                Long mngStaffId = Long.parseLong(request.getParameter("mngStfId"));
                ManagingStaff mngStf = managingStaffFacade.find(mngStaffId);

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
                    UserAccount user = userAccountFacade.find(mngStf.getUserAccountID());

                    mngStf.setFullName(fullName);
                    mngStf.setAge(age);
                    user.setPassword(password);
                    mngStf.setCity(city);
                    mngStf.setAddressLine1(addressLine1);
                    mngStf.setAddressLine2(addressLine2);
                    mngStf.setPhoneNumber(phoneNumber);
                    mngStf.setEmail(email);

                    managingStaffFacade.edit(mngStf);
                    userAccountFacade.edit(user);
                    request.setAttribute("userType", "managingStaff");

                    request.getRequestDispatcher("LoadUserInfoServlet").forward(request, response);
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
