/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.saleManager;

import entity.OrderStatus;
import entity.ShopOrder;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.OrderStatusDAO;
import model.ShopOrderDAO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "SaleServlet", urlPatterns = {"/saler"})
public class SaleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        if (curUser != null && curUser.getRole() == 2) {
            ShopOrderDAO orderDAO = new ShopOrderDAO();
            OrderStatusDAO osd = new OrderStatusDAO();
            List<ShopOrder> listOrder = orderDAO.findAllForSale(curUser.getUserID());
            List<OrderStatus> listOrderStatus = osd.findAll();
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("listOrderStatus", listOrderStatus);
            // Thiết lập attribute 'sales' cho request
            request.getRequestDispatcher("sale.jsp").forward(request, response);
        } else {
            response.sendRedirect("login-servlet");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        ShopOrderDAO orderDAO = new ShopOrderDAO();
        if (curUser != null && curUser.getRole() == 2) {
            int id = Integer.parseInt(request.getParameter("id"));
            int status = Integer.parseInt(request.getParameter("status"));
            try {
                orderDAO.updateStatusOrder(status, id);
                session.setAttribute("msg", "Status update successfully !");
            } catch (Exception e) {
                session.setAttribute("err", "Status update Failed : "+e.getMessage());
            }
            response.sendRedirect("saler");
            
        } else {
            response.sendRedirect("login-servlet");
        }
    }
    
}
