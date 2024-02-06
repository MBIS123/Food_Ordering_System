/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Customer;
import model.CustomerFacade;
import model.ManagingStaff;
import model.ManagingStaffFacade;
import model.StallStaff;
import model.StallStaffFacade;
import model.UserAccount;
import model.UserAccountFacade;
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
import model.MenuItem;
import model.MenuItemFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private MenuItemFacade menuItemFacade;

    @EJB
    private ManagingStaffFacade managingStaffFacade;

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private UserAccountFacade userAccountFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Enter loginServlet");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                String username = request.getParameter("username");
                UserAccount found = userAccountFacade.findByUsername(username);
                String password = request.getParameter("password");
                Long id = found.getId();

                boolean loginFlag = true;

                if (found == null) {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('User does not exist, please re-enter or register!');");
                    out.println("history.back();");
                    out.println("</script>");
                } else if (!password.equals(found.getPassword())) {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Password incorrect, please re-enter !!');");
                    out.println("history.back();");
                    out.println("</script>");
                } else if ("customer".equals(found.getRole())
                        && !customerFacade.findCustomerByUserAccountId(id).isActive()) {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Customer Accound was deleted by Managing Staff!');");
                    out.println("history.back();");
                    out.println("</script>");
                } else if ("customer".equals(found.getRole())
                        && !customerFacade.findCustomerByUserAccountId(id).isActive()) {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Customer Accound was deleted by Managing Staff!');");
                    out.println("history.back();");
                    out.println("</script>");
                } else {
                    redirectToUserHome(request, response, found, id);

                }

            } catch (Exception e) {
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.println("<br><br><br>Wrong input! User not exist");
                e.printStackTrace();
            }
        }
    }

    private void redirectToUserHome(HttpServletRequest request, HttpServletResponse response, UserAccount found, Long id)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("login", found);

        switch (found.getRole()) {
            case "customer":
                Customer cusInfo = customerFacade.findCustomerByUserAccountId(id);
                session.setAttribute("cusInfo", cusInfo);
                request.setAttribute("action", "backHome");
                request.getRequestDispatcher("LoadCustomerManageServlet").include(request, response);
                break;
            case "stallStaff":
                StallStaff staffInfo = stallStaffFacade.findStallStaffByUserAccountId(id);
                session.setAttribute("staffInfo", staffInfo);
                request.setAttribute("action", "backHome");
                request.getRequestDispatcher("LoadStallStaffManageServlet").include(request, response);
                break;
            case "managingStaff":
                ManagingStaff magStfInfo = managingStaffFacade.findManagingStaffByUserAccountId(id);
                System.out.println("magStfInfo: " + magStfInfo); // Debugging line
                session.setAttribute("magStfInfo", magStfInfo);
                request.setAttribute("userType", "manageStaffReport");
                request.setAttribute("action", "loadReport");
                request.getRequestDispatcher("LoadUserInfoServlet").include(request, response);
                break;
            default:
                request.getRequestDispatcher("loginError.jsp").include(request, response);
                break;
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
