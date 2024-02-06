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
import model.MenuItem;
import model.MenuItemFacade;
import model.Order;
import model.OrderFacade;
import model.StallStaff;

/**
 *
 * @author User
 */
@WebServlet(name = "LoadStallStaffManageServlet", urlPatterns = {"/LoadStallStaffManageServlet"})
public class LoadStallStaffManageServlet extends HttpServlet {

    @EJB
    private OrderFacade orderFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private MenuItemFacade menuFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");
            Long stallId = staffInfo.getStallId();
            System.out.println("stallid:" + stallId.toString());

            if ("editMenu".equals(action)) {
                System.out.println("start loading menu");
                try {
                    List<MenuItem> menuItems = menuFacade.findByStallId(stallId); // Fetch all menu items
                    if (menuItems != null) {
                        System.out.println("menu here by facade");
                        System.out.println("menuitem lenght:" + menuItems.size());
                    }
                    request.setAttribute("menuItems", menuItems);
                    request.getRequestDispatcher("stallManageMenu.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("failed load menu");

                    e.printStackTrace();
                }
            } else if ("profile".equals(action)) {
                request.setAttribute("staffInfo", staffInfo);
                request.getRequestDispatcher("stallStaffProfile.jsp").forward(request, response);

            } else if ("backHome".equals(action)) {

                List<Order> orderItems = orderFacade.findOrdersByStallId(stallId);
                request.setAttribute("orders", orderItems);
                request.getRequestDispatcher("stallStaffHome.jsp").forward(request, response);

            } else if ("salesRecord".equals(action)) {
                List<Order> orderItems = orderFacade.findPaidOrdersByStallId(stallId);
                request.setAttribute("orders", orderItems);
                request.getRequestDispatcher("stallStaffSaleRecord.jsp").forward(request, response);

            } else if ("stallReport".equals(action)) {
                List<Order> orderItems = orderFacade.findPaidOrdersByStallId(stallId);
                request.setAttribute("orders", orderItems);

                Long paidOrdersCount = orderFacade.countPaidOrdersByStallId(stallId);

                List<Object[]> menuItemCounts = orderFacade.findMenuItemCountsByStallIdAndPaidStatus(stallId);
                List<Object[]> ageGroupCounts = orderFacade.countDistinctCustomersByAgeGroupsAndStallId(stallId);
                List<Object[]> genderCounts = orderFacade.countDistinctCustomersByGenderAndStallId(stallId);
                List<Object[]> raceCounts = orderFacade.countDistinctCustomersByRaceAndStallId(stallId);
                List<Object[]> menuItemRatings = orderFacade.findAverageRatingsByMenuItemAndStallId(stallId);

                request.setAttribute("menuItemRatings", menuItemRatings);

                request.setAttribute("raceCounts", raceCounts);

                request.setAttribute("genderCounts", genderCounts);

                request.setAttribute("ageGroupCounts", ageGroupCounts);

                request.setAttribute("menuItemCounts", menuItemCounts);

                request.setAttribute("paidOrdersCount", paidOrdersCount);

                request.getRequestDispatcher("stallStaffReport.jsp").forward(request, response);

            } else if ("editSelectedMenuItem".equals(action)) {
                String itemId = request.getParameter("itemId");
                MenuItem menuItem = menuFacade.find(Long.parseLong(itemId));

                request.setAttribute("menuItem", menuItem);
                request.getRequestDispatcher("edit.jsp").forward(request, response);

            } else {
                System.out.println("noneaction:" + action);
            }

        }

    }

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
            String editMenu = request.getParameter("editItem");
            HttpSession session = request.getSession();
            StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");
            Long stallId = staffInfo.getStallId();
            System.out.println("stallid:" + stallId.toString());

            if ("editMenu".equals(action)) {
                System.out.println("start loading menu");
                try {

                    List<MenuItem> menuItems = menuFacade.findByStallId(stallId); // Fetch all menu items
                    if (menuItems != null) {
                        System.out.println("menu here by facade");
                        System.out.println("menuitem lenght:" + menuItems.size());
                    }
                    request.setAttribute("menuItems", menuItems);
                    request.getRequestDispatcher("stallManageMenu.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("failed load menu");

                    e.printStackTrace();
                }
            } else if ("profile".equals(action)) {
                request.setAttribute("staffInfo", staffInfo);
                request.getRequestDispatcher("stallStaffProfile.jsp").forward(request, response);

            } else if ("backHome".equals(action)) {

                List<Order> orderItems = orderFacade.findOrdersByStallId(stallId);
                request.setAttribute("orders", orderItems);
                request.getRequestDispatcher("stallStaffHome.jsp").forward(request, response);

            } else if ("salesRecord".equals(action)) {
                request.getRequestDispatcher("stallStaffSaleRecord.jsp").forward(request, response);

            } else if ("editSelectedMenuItem".equals(editMenu)) {

                String itemId = request.getParameter("itemId");
                MenuItem menuItem = menuFacade.find(Long.parseLong(itemId));

                request.setAttribute("menuItem", menuItem);

//                String itemPrice = request.getParameter("itemPrice");
//                String itemCategory = request.getParameter("itemCategory");
//                String itemImgUrl = request.getParameter("itemImgUrl");
//                
                request.getRequestDispatcher("editMenu.jsp").forward(request, response);
            } else {
                System.out.println("none actionis:" + action);
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
