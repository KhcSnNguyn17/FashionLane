package controller.user;

import entity.Address;
import entity.Product;
import entity.ShopOrder;
import entity.Variation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lấy thông tin từ yêu cầu
        HttpSession session = request.getSession();
        Address tempAddress = (Address) request.getSession().getAttribute("temporaryAddress");
        if (tempAddress != null) {
            String PayType = String.valueOf(request.getSession().getAttribute("PayType"));
            if (PayType.equals("null")) {
                request.setAttribute("PayMessError", "You must choose a payment method");
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else {
                int userID = Integer.parseInt(request.getParameter("UserID"));
                CartItemDAO cartItemDAO = new CartItemDAO();
                ProductDAO pd = new ProductDAO();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                AddressDAO ad = new AddressDAO();
                List<Product> cartItems = cartItemDAO.getUserItem(userID);
                ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
                ShopOrder shopOrder = shopOrderDAO.getLatestOrder();
                ShopOrder shopOrderReturn = (ShopOrder) session.getAttribute("shopOrder");
                if (shopOrderReturn == null) {
                    response.sendRedirect("cart");
                    return;
                }
                int orderID = shopOrder.getShop_orderID();
                double totalOrderAmount = 0;
                for (Product cartItem : cartItems) {
                    String productID = String.valueOf(cartItem.getProductID());
                    String variationID = String.valueOf(cartItem.getVariationID());
                    int quantity = cartItem.getQty_in_cart();
                    double price = cartItem.getPrice() * quantity;
                    totalOrderAmount += price;
                    orderDetailDAO.insert(orderID, productID, variationID, quantity, price);
                    VariationDAO variationDAO = new VariationDAO();
                    Variation var = variationDAO.getVariation(productID, cartItem.getColor_Name(), cartItem.getSize_Name());
                    variationDAO.updateVariation(cartItem.getProductID(), var.getColor_ID(), var.getSize_ID(), var.getQtu_in_stock() - cartItem.getQty_in_cart(), var.getProduct_img_ID(), var.getVariationID());
                    if (!PayType.equals("VNPAY")) {
                        cartItemDAO.deleteCartItemByProdID(productID, variationID);
                        pd.reduceQuantityOfProduct(productID, variationID, quantity);
                    }
                }
                shopOrderDAO.setOrderTotal();
                ad.setAddressIDtoShopOrder();
                int saleId = shopOrderDAO.getSaleWithMinOrder();
                shopOrderDAO.insertSaleAssignment(orderID, saleId);
                if (PayType.equals("VNPAY")) {
                    session.setAttribute("orderId", orderID);
                    long paymentPrice = (long) Math.round(totalOrderAmount + 36000);
                    String paymentUrl = "vnpay" + "?amount=" + paymentPrice;
                    response.sendRedirect(paymentUrl);
                } else {
                    session.removeAttribute("temporaryAddress");
                    session.removeAttribute("PayType");
                    request.setAttribute("OrderMessage", "Order Placed");
                    request.setAttribute("TotalOrderAmount", totalOrderAmount);
                    request.getRequestDispatcher("thanks.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("OrderMessageError", "You must enter shipping information");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
