/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.saleManager;

import entity.OrderStatus;
import entity.ShopOrder;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.OrderStatusDAO;
import model.ShopOrderDAO;
import model.UserDAO;

@WebServlet(name = "SaleManagerServlet", urlPatterns = {"/saleManager"})
public class SaleManagerServlet extends HttpServlet {

    private ShopOrderDAO orderDAO = null;
    private OrderStatusDAO osd = null;
    private UserDAO userDAO = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("acc");
        if (user == null || (user != null && user.getRole() != 5)) {
            response.sendRedirect("login-servlet");
            return;
        }
        //khai bao order dao
        orderDAO = new ShopOrderDAO();
        osd = new OrderStatusDAO();
        userDAO = new UserDAO();
        //get ve toan bo order
        List<ShopOrder> listOrder = orderDAO.findAll();
        List<OrderStatus> listOrderStatus = osd.findAll();
        // Lấy danh sách Sales từ DAO
        List<User> sales = userDAO.selectAllSalesName();
        //set vao request
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("listOrderStatus", listOrderStatus);
        // Thiết lập attribute 'sales' cho request
        request.setAttribute("sales", sales);
        //chuyen trang
        request.getRequestDispatcher("saleManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("acc");
        ShopOrderDAO orderDAO = new ShopOrderDAO();
        if (curUser != null && curUser.getRole() == 5) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String userId = request.getParameter("userId");
            if (userId.equals("none")) {
                response.sendRedirect("saleManager");
                return;
            }
            try {
                orderDAO.updateSaleAssignment(orderId, Integer.parseInt(userId));
                session.setAttribute("msg", "Assign order successfully !");
            } catch (Exception e) {
                session.setAttribute("err", "Assign order Failed : " + e.getMessage());
            }
            response.sendRedirect("saleManager");

        } else {
            response.sendRedirect("login-servlet");
        }
    }

}
