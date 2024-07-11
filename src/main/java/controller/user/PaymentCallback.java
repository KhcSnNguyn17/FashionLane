/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.OrderDetail;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AddressDAO;
import model.CartItemDAO;
import model.OrderDetailDAO;
import model.ProductDAO;
import model.ShopOrderDAO;


/**
 *
 * @author HP
 */
@WebServlet(name = "PaymentCallback", urlPatterns = {"/PaymentCallback"})
public class PaymentCallback extends HttpServlet {

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
            out.println("<title>Servlet PaymentCallback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentCallback at " + request.getContextPath() + "</h1>");
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
        int orderID = 0;
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
        try {
            orderID = Integer.parseInt(session.getAttribute("orderId") + "");
            User user = (User) session.getAttribute("acc");
            String transactionStatus = request.getParameter("vnp_TransactionStatus");
            System.out.println(transactionStatus);
            if ("00".equals(transactionStatus)) {
                int userID = user != null ? user.getUserID() : -1;
                request.setAttribute("OrderMessage", "Order Placed Successfully");
                session.removeAttribute("temporaryAddress");
                session.removeAttribute("PayType");
                session.removeAttribute("shopOrder");
                request.setAttribute("OrderMessage", "Order Placed");
                CartItemDAO cartItemDAO = new CartItemDAO();
                ProductDAO pd = new ProductDAO();
                AddressDAO ad = new AddressDAO();
                List<Product> cartItems = cartItemDAO.getUserItem(userID);
                for (Product cartItem : cartItems) {
                    String productID = String.valueOf(cartItem.getProductID());
                    String variationID = String.valueOf(cartItem.getVariationID());
                    int quantity = cartItem.getQty_in_cart();
                    cartItemDAO.deleteCartItemByProdID(productID, variationID);
                    pd.reduceQuantityOfProduct(productID, variationID, quantity);
                }
                request.getRequestDispatcher("thanks.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
        }
        System.out.println("OrderId: " + orderID);
        ProductDAO productDAO = new ProductDAO();
        List<OrderDetail> orderDetails = orderDetailDAO.getOrderItems(orderID + "");
        for (OrderDetail orderDetail : orderDetails) {
            productDAO.increaseQuantityOfProduct(orderDetail.getProductID() + "", orderDetail.getVariationID() + "", orderDetail.getQuantity());
        }
        orderDetailDAO.deleteOrderDetailsByOrderID(orderID);
        shopOrderDAO.deleteSaleAssignmentByOrderID(orderID);
        shopOrderDAO.deleteOrderByOrderID(orderID);
        response.sendRedirect("cart?error=Payment Failed. Try again");
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
