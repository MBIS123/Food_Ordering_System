/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

/**
 *
 * @author User
 */
@WebServlet(name = "EditMenuServlet", urlPatterns = {"/EditMenuServlet"})
public class EditMenuServlet extends HttpServlet {

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

                System.out.println("start edit menu");
                String itemType = request.getParameter("itemType");
                String itemName = request.getParameter("itemName");
                Double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
                Long itemId = Long.parseLong(request.getParameter("itemId"));
                String itemImg = request.getParameter("itemImg");

                menuFacade.editMenu(itemId, itemName, itemType, itemPrice, itemImg);
                request.setAttribute("action", "editMenu");
                request.getRequestDispatcher("LoadStallStaffManageServlet").forward(request, response);

            } catch (Exception e) {
                System.out.println("failed edit menu");
                out.println("<script type='text/javascript'>");
                out.println("alert('Image file/url too large, please re-enter anoter image with shorter url!');");
                out.println("history.back();");
                out.println("</script>");

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
