/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ManagingStaffFacade;
import model.UserAccountFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.CustomerFacade;
import model.ManagingStaff;
import model.OrderFacade;
import model.Stall;
import model.StallFacade;
import model.StallStaff;
import model.StallStaffFacade;
import model.StallStaffPending;
import model.StallStaffPendingFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "ManageManagingStaffServlet", urlPatterns = {"/ManageManagingStaffServlet"})
public class ManageManagingStaffServlet extends HttpServlet {

    @EJB
    private StallFacade stallFacade;

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private StallStaffPendingFacade stallStaffPendingFacade;

    @EJB
    private OrderFacade orderFacade;

    @EJB
    private UserAccountFacade userAccountFacade;

    @EJB
    private ManagingStaffFacade managingStaffFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            System.out.println("Entered manageManagingStaffServlet");

            String action = request.getParameter("action");
            String user = request.getParameter("user");
            String userAccoundID = request.getParameter("userID").trim();
            Long userTypeId = Long.parseLong(request.getParameter("userTypeID").trim());
            String manageType = request.getParameter("manageType");

            System.out.println("Actionmngstfsevlet:" + action);
            System.out.println("usertfsevlet:" + user);

            if ("managingStaff".equals(user)) {
                if ("delete".equals(action)) {
                    System.out.println(" going to loadserinfo1");

                    managingStaffFacade.deleteManagingStaffById(userTypeId);
                    userAccountFacade.deleteUserAccountById(Long.parseLong(userAccoundID));
                    String userType = "managingStaff";
                    request.setAttribute("userType", userType); // Set menu items in the request scope
                    System.out.println(" going to loadserinfo2");

                    RequestDispatcher dispatcher = request.getRequestDispatcher("LoadUserInfoServlet");
                    dispatcher.forward(request, response);

                    System.out.println(" going to loadserinfo3");

                } else if ("edit".equals(action)) {

                    ManagingStaff managingStaffInfo = managingStaffFacade.find(userTypeId);
                    request.setAttribute("managingStaffInfo", managingStaffInfo);

                    request.getRequestDispatcher("updateManagingStaffInfo.jsp").forward(request, response);

                }
            } else if ("stallStaff".equals(user)) {
                request.setAttribute("userType", "stallStaff");

                if ("approve".equals(action)) {
                    List<Stall> stallInfo = stallFacade.findAll();
                    String pendingId = request.getParameter("staffPendingId");
                    request.setAttribute("stallInfo", stallInfo);

                    request.setAttribute("staffPendingId", pendingId);
                    request.getRequestDispatcher("approveStallStaff.jsp").include(request, response);
                } else if ("reject".equals(action)) {
                    Long pendingId = Long.parseLong(request.getParameter("staffPendingId"));
                    StallStaffPending pendStaff = stallStaffPendingFacade.find(pendingId);
                    pendStaff.setStatus("Rejected");
                    stallStaffPendingFacade.edit(pendStaff);
                    request.getRequestDispatcher("LoadUserInfoServlet").forward(request, response);

                } else if ("edit".equals(action)) {

                    StallStaff stallStaffInfo = stallStaffFacade.find(userTypeId);
                    request.setAttribute("stallStaffInfo", stallStaffInfo);

                    request.getRequestDispatcher("updateStallStaffInfo.jsp").forward(request, response);

                } else if ("delete".equals(action)) {
                    stallStaffFacade.deleteStallStaffById(userTypeId);
                    userAccountFacade.deleteUserAccountById(Long.parseLong(userAccoundID));
                    request.getRequestDispatcher("LoadUserInfoServlet").forward(request, response);

                }

            } else if ("customer".equals(user)) {
                request.setAttribute("userType", "customer");

                if ("edit".equals(action)) {
                    Customer customerInfo = customerFacade.find(userTypeId);
                    request.setAttribute("customerInfo", customerInfo);
                    request.getRequestDispatcher("updateCustomerInfo.jsp").forward(request, response);

                } else if ("delete".equals(action)) {

                    Customer cus = customerFacade.find(userTypeId);
                    cus.setIsActive(false);
                    customerFacade.edit(cus);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("LoadUserInfoServlet");
                    dispatcher.forward(request, response);
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
