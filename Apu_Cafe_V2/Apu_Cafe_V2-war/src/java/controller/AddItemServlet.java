/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import model.MenuItem;
import model.MenuItemFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ManageMenu;
import model.Stall;
import model.StallFacade;
import model.StallStaff;

/**
 *
 * @author User
 */
@WebServlet(name = "AddItemServlet", urlPatterns = {"/AddItemServlet"})
public class AddItemServlet extends HttpServlet {

    @EJB
    private StallFacade stallFacade;

    @EJB
    private ManageMenu addMenuItem;

    @EJB
    private MenuItemFacade menuFacade;

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

            try {

                System.out.println("Start addmenu 1");

                String itemType = request.getParameter("itemType");
                String itemName = request.getParameter("itemName");
                String itemCategory = request.getParameter("itemCategory");
                String itemImgUrl = request.getParameter("itemImg");
                Double price = Double.parseDouble(request.getParameter("itemPrice"));
                HttpSession session = request.getSession();
                StallStaff staffInfo = (StallStaff) session.getAttribute("staffInfo");
                Stall staffObj = stallFacade.find(staffInfo.getStallId());

                MenuItem newItem = new MenuItem(itemName, price, itemCategory, itemImgUrl, staffObj);
                addMenuItem.addItem(menuFacade, newItem);
                System.out.println("item added");
                String action = "editMenu";
                request.setAttribute("action", action);
                request.getRequestDispatcher("LoadStallStaffManageServlet").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<script type='text/javascript'>");
                    out.println("alert('Image file/url too large, please re-enter anoter image with shorter url!');");
                    out.println("history.back();");
                    out.println("</script>");
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
