/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.saleManager;

import entity.DailySaleRevenue;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import model.ShopOrderDAO;

/**
 *
 * @author HP
 */
public class DashboardSaleController extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardSaleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardSaleController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        if (curUser != null && curUser.getRole() == 2) {
            int saleId = curUser.getUserID();
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            boolean validDates = true;
            String errorMessage = "";

            if (startDate != null && !startDate.isEmpty()) {
                try {
                    sdf.parse(startDate);
                } catch (Exception e) {
                    validDates = false;
                    errorMessage = "Invalid start date format.";
                }
            }

            if (endDate != null && !endDate.isEmpty()) {
                try {
                    sdf.parse(endDate);
                } catch (Exception e) {
                    validDates = false;
                    errorMessage = "Invalid end date format.";
                }
            }

            if (validDates && startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()) {
                try {
                    if (sdf.parse(startDate).after(sdf.parse(endDate))) {
                        validDates = false;
                        errorMessage = "Start date cannot be after end date.";
                    }
                } catch (Exception e) {
                    validDates = false;
                    errorMessage = "Invalid date comparison.";
                }
            }
            List<DailySaleRevenue> dailySaleRevenues = null;
            ShopOrderDAO shopDao = new ShopOrderDAO();
            if (validDates) {
                request.setAttribute("start", startDate);
                request.setAttribute("end", endDate);
                dailySaleRevenues = shopDao.getSuccessfullyCompletedOrders(saleId, startDate, endDate);
                request.setAttribute("dailySaleRevenues", dailySaleRevenues);
                request.getRequestDispatcher("dashboardSaler.jsp").forward(request, response);
            } else {
                dailySaleRevenues = shopDao.getSuccessfullyCompletedOrders(saleId, null, null);
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("dailySaleRevenues", dailySaleRevenues);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardSaler.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("login-servlet");
        }
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
