/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Stall;
import model.StallFacade;
import model.StallStaffFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "CreateStoreServlet", urlPatterns = {"/CreateStoreServlet"})
public class CreateStoreServlet extends HttpServlet {

    @EJB
    private StallFacade stallFacade;

    @EJB
    private StallStaffFacade stallStaffFacade;

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

            System.out.println("Inside createstore servlet");
            String stallName = request.getParameter("stallName");
            String storeCategory = request.getParameter("storeCategory");

            if (stallFacade.findStallByStoreName(stallName) == null) {

                Stall obj = new Stall(stallName, storeCategory);
                try {
                    stallFacade.create(obj);
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Stall created successfully, Please Add Staff to Stall');");
                    out.println("</script>");
                    request.setAttribute("userType", "stallStaff");
                    request.getRequestDispatcher("LoadUserInfoServlet").include(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<script type='text/javascript'>alert('Create Store failed! Store already exist!');</script>");
                request.getRequestDispatcher("createStore.jsp").include(request, response);
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
