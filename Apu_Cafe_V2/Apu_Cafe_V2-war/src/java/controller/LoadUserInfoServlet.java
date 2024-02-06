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
import model.StallStaffPending;
import model.StallStaffPendingFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderFacade;
import model.StallFacade;
import model.UserAccountFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "LoadUserInfoServlet", urlPatterns = {"/LoadUserInfoServlet"})
public class LoadUserInfoServlet extends HttpServlet {

    @EJB
    private UserAccountFacade userAccountFacade;

    @EJB
    private StallFacade stallFacade;

    @EJB
    private OrderFacade orderFacade;

    @EJB
    private StallStaffPendingFacade stallStaffPendingFacade;

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private ManagingStaffFacade managingStaffFacade;

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
    // used to load info for managing UI
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            System.out.println("start loading user info");
            String action = request.getParameter("action");

            try {
                if ("manageCustomerInfo".equals(action)) {
                    List<Customer> cusInfo = customerFacade.findAll(); 
                    request.setAttribute("cusInfo", cusInfo); 
                    request.getRequestDispatcher("manageCustomerInfo.jsp").forward(request, response); 
                } else if ("manageStallStaff".equals(action)) {
                    List<StallStaff> stallStaffInfo = stallStaffFacade.findAll(); 
                    List<StallStaffPending> stallStaffPendingInfo = stallStaffPendingFacade.findAll(); 
                    request.setAttribute("stallStaffInfo", stallStaffInfo); 
                    request.setAttribute("stallStaffPendingInfo", stallStaffPendingInfo); 
                    request.getRequestDispatcher("manageStallStaff.jsp").forward(request, response);    
                } else if ("manageManagingStaff".equals(action)) {
                    List<ManagingStaff> mngStaffInfo = managingStaffFacade.findAll(); 
                    request.setAttribute("mngStaffInfo", mngStaffInfo);
                    request.getRequestDispatcher("manageManagingStaff.jsp").forward(request, response); 
                } else if ("backHome".equals(action)) {
                    List<StallStaff> stallStaffInfo = stallStaffFacade.findAll(); 
                    List<StallStaffPending> stallStaffPendingInfo = stallStaffPendingFacade.findAll(); 
                    request.setAttribute("stallStaffInfo", stallStaffInfo); 
                    request.setAttribute("stallStaffPendingInfo", stallStaffPendingInfo);

                    List<Object[]> topStores = orderFacade.findTop5StoresByPaidOrders();

                    // Set the result as an attribute for the JSP
                    List<Object[]> stallTypeandCount = stallFacade.groupStallsByTypeAndCount();

                    List<Object[]> staffEachStore = stallFacade.getStallNamesAndStaffCount();
                    Map<String, Long> userCountsByRole = userAccountFacade.countUsersByRole();

                    List<Object[]> countStaffByGender = stallStaffFacade.countStaffByGender();

                    request.setAttribute("userCountsByRole", userCountsByRole);

                    request.setAttribute("topStores", topStores);
                    request.setAttribute("stallCounts", stallTypeandCount);
                    request.setAttribute("stallStaffCountStore", staffEachStore);
                    request.setAttribute("cntStaffGender", countStaffByGender);

                    request.getRequestDispatcher("managingStaffHome.jsp").forward(request, response); 
                }

            } catch (Exception e) {
                System.out.println("failed load menu");

                e.printStackTrace();
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String userType = (String) request.getAttribute("userType");
            System.out.println("enter back loaduserinfoServlet:" + userType);
            if ("managingStaff".equals(userType)) {
                System.out.println(" is managemanagingstaff here");

                List<ManagingStaff> mngStaffInfo = managingStaffFacade.findAll(); 
                request.setAttribute("mngStaffInfo", mngStaffInfo); 
                request.getRequestDispatcher("manageManagingStaff.jsp").forward(request, response); 
            } else if ("customer".equals(userType)) {
                System.out.println(" is managemanagingstaff here");
                List<Customer> cusInfo = customerFacade.findAll();
                request.setAttribute("cusInfo", cusInfo);
                request.getRequestDispatcher("manageCustomerInfo.jsp").forward(request, response); 
            } else if ("stallStaff".equals(userType)) {
                System.out.println(" is stall Staff here");
                List<StallStaff> stallStaffInfo = stallStaffFacade.findAll(); 
                List<StallStaffPending> stallStaffPendingInfo = stallStaffPendingFacade.findAll(); 
                request.setAttribute("stallStaffInfo", stallStaffInfo); 
                request.setAttribute("stallStaffPendingInfo", stallStaffPendingInfo); 
                request.getRequestDispatcher("manageStallStaff.jsp").forward(request, response); 

            } else if ("manageStaffReport".equals(userType)) {

                List<StallStaff> stallStaffInfo = stallStaffFacade.findAll(); 
                List<StallStaffPending> stallStaffPendingInfo = stallStaffPendingFacade.findAll(); 
                request.setAttribute("stallStaffInfo", stallStaffInfo);
                request.setAttribute("stallStaffPendingInfo", stallStaffPendingInfo);

                List<Object[]> topStores = orderFacade.findTop5StoresByPaidOrders();

                // Set the result as an attribute for the JSP
                List<Object[]> stallTypeandCount = stallFacade.groupStallsByTypeAndCount();

                List<Object[]> staffEachStore = stallFacade.getStallNamesAndStaffCount();
                Map<String, Long> userCountsByRole = userAccountFacade.countUsersByRole();

                List<Object[]> countStaffByGender = stallStaffFacade.countStaffByGender();

                request.setAttribute("userCountsByRole", userCountsByRole);

                request.setAttribute("topStores", topStores);
                request.setAttribute("stallCounts", stallTypeandCount);
                request.setAttribute("stallStaffCountStore", staffEachStore);
                request.setAttribute("cntStaffGender", countStaffByGender);

                request.getRequestDispatcher("managingStaffHome.jsp").forward(request, response); 

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
