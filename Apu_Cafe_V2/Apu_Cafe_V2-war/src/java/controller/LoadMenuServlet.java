/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MenuItem;
import model.MenuItemFacade;
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
import model.Stall;
import model.StallStaff;

/**
 *
 * @author User
 */
@WebServlet(name = "LoadMenuServlet", urlPatterns = {"/LoadMenuServlet"})
public class LoadMenuServlet extends HttpServlet {

    @EJB
    private MenuItemFacade menuFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");

            if ("editMenu".equals(action)) {
                System.out.println("start loading menu");
                try {
                    List<MenuItem> menuItems = menuFacade.findAll(); // Fetch all menu items
                    System.out.println("menu here");
                    HttpSession session = request.getSession();
                    StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");
                    System.out.println("staffInfoName:" + staffInfo.getUsername());
                    String stallName = staffInfo.getStallName();
                    List<MenuItem> stallMenuItems = new ArrayList<>();
                    for (MenuItem menu : menuItems) {
                        if (stallName.equals(menu.getStallName())) {
                            System.out.println("menu.getStallName is:" + menu.getStallName());
                            stallMenuItems.add(menu);
                        }
                    }
                    request.setAttribute("menuItems", stallMenuItems);
                    request.getRequestDispatcher("stallManageMenu.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("failed load menu");

                    e.printStackTrace();
                }
          
        }

    }}

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
            System.out.println("action loadmenu:"+action);

            if ("editMenu".equals(action)) {
                System.out.println("start loading menu123");
                try {
                    List<MenuItem> menuItems = menuFacade.findAll(); // Fetch all menu items
                    System.out.println("menu here");
                    HttpSession session = request.getSession();
                    StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");
                    System.out.println("staffInfoName:" + staffInfo.getUsername());
                    String stallName = staffInfo.getStallName();
                    List<MenuItem> stallMenuItems = new ArrayList<>();
                    for (MenuItem menu : menuItems) {
                        if (stallName.equals(menu.getStallName())) {
                            System.out.println("menu.getStallName is:" + menu.getStallName());
                            stallMenuItems.add(menu);
                        }
                    }
                    request.setAttribute("menuItems", stallMenuItems);
                    request.getRequestDispatcher("stallManageMenu.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("failed load menu");

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
