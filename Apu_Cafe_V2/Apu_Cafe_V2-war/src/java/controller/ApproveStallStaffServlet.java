/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Stall;
import model.StallFacade;
import model.StallStaff;
import model.StallStaffFacade;
import model.StallStaffPending;
import model.StallStaffPendingFacade;
import model.StallStaffRegistrationService;

import model.UserRegistration;

/**
 *
 * @author User
 */
@WebServlet(name = "ApproveStallStaffServlet", urlPatterns = {"/ApproveStallStaffServlet"})
public class ApproveStallStaffServlet extends HttpServlet {

    @EJB
    private StallStaffFacade stallStaffFacade;

    @EJB
    private StallFacade stallFacade;

    @EJB
    private StallStaffPendingFacade stallStaffPendingFacade;

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
            /* TODO output your page here. You may use following sample code. */

            System.out.println("Entered ApproveStallStaffServlet");
            String userAccoundID = request.getParameter("action").trim();
            String stallId = request.getParameter("stallID").trim();
            String stallStaffPenID = request.getParameter("staffPendingId").trim();
            System.out.println("In  approve stall staff servlet stallID IS:" + stallId);
            System.out.println("In  approve stall staff servlet staffStaffPebdingID IS:" + stallStaffPenID);

            StallStaffPending pendingStaff = stallStaffPendingFacade.find(Long.parseLong(stallStaffPenID));

            Stall stall = stallFacade.find(Long.parseLong(stallId));

            String stallName = stall.getStoreName();

            String username = pendingStaff.getUserName();
            String password = pendingStaff.getPassword();
            String fullName = pendingStaff.getFullName();
            String email = pendingStaff.getEmail();
            String phoneNo = pendingStaff.getPhoneNumber();
            String userType = "stallStaff";
            String city = pendingStaff.getCity();
            String addressLine1 = pendingStaff.getAddressLine1();
            String addressLine2 = pendingStaff.getAddressLine2();
            String gender = pendingStaff.getGender();
            String race = pendingStaff.getRace();
            int age = pendingStaff.getAge();

            StallStaff newStallStaff = new StallStaff(username, password, fullName, email, phoneNo, userType,
                    city, addressLine1, addressLine2, gender, race, age, stall);

            try {
                

                stallStaffFacade.create(newStallStaff);
                pendingStaff.setStatus("Approved");
                stallStaffPendingFacade.edit(pendingStaff);

                String action = request.getParameter("joinOrCreateStall");
                out.println("<script type='text/javascript'>");
                out.println("alert('Staff successfully added');");
                out.println("</script>");
                request.setAttribute("userType", "stallStaff");
                    request.getRequestDispatcher("LoadUserInfoServlet").include(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                                out.println("<script type='text/javascript'>");
                out.println("alert('Staff fail to add');history.back();");
                out.println("</script>");
                
            }
        }
//            StallStaffPending pendingStallStaff = new StallStaffPending("Pending", username, password, fullName, email, phoneNo,
//                    city, addressLine1, addressLine2, gender, race, age);
//            stallStaffPendingFacade.create(pendingStallStaff);
//            out.println("<script type='text/javascript'>alert('Your Stall Staff Registration request was made successfully/n"
//                    + " You are only able to log in when your request is approved!');</script>");
//
//            request.getRequestDispatcher("login.jsp").include(request, response);
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
