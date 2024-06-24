package controller.user;

import entity.CartItem;
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
            System.out.println(PayType);
            if (PayType.equals("null")) {
                request.setAttribute("PayMessError", "You must chooser payment method");
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else if (PayType.equals("VNPAY")) {
                request.getRequestDispatcher("ajaxServlet").forward(request, response);
            } else {
                int userID = Integer.parseInt(request.getParameter("UserID"));
                CartItemDAO cartItemDAO = new CartItemDAO();
                ProductDAO pd = new ProductDAO();
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                AddressDAO ad = new AddressDAO();
                List<Product> cartItems = cartItemDAO.getUserItem(userID);

                // Tạo đơn hàng
                ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
                ShopOrder shopOrder = shopOrderDAO.getLatestOrder();
                int orderID = shopOrder.getShop_orderID();

                // Đặt hàng cho từng sản phẩm trong giỏ hàng
                for (Product cartItem : cartItems) {
                    String productID = String.valueOf(cartItem.getProductID());
                    String variationID = String.valueOf(cartItem.getVariationID());
                    int quantity = cartItem.getQty_in_cart();  // Lấy số lượng từ giỏ hàng
                    double price = cartItem.getPrice() * quantity;  // Tính toán giá trị dựa trên số lượng

                    // Thực hiện đặt hàng cho sản phẩm
                    orderDetailDAO.insert(orderID, productID, variationID, quantity, price);
                    VariationDAO variationDAO = new VariationDAO();

                    //Giảm quanty in stock
                    Variation var = variationDAO.getVariation(productID, cartItem.getColor_Name(), cartItem.getSize_Name());
                    variationDAO.updateVariation(cartItem.getProductID(), var.getColor_ID(), var.getSize_ID(), var.getQtu_in_stock() - cartItem.getQty_in_cart(), var.getProduct_img_ID(), var.getVariationID());

                    // Xóa sản phẩm đã đặt hàng khỏi giỏ hàng
                    cartItemDAO.deleteCartItemByProdID(productID, variationID);
                    pd.reduceQuantityOfProduct(productID, variationID, quantity);
                    ad.setAddressIDtoShopOrder();
                    shopOrderDAO.setOrderTotal();
                }
                System.out.println(orderID);
                int saleId = shopOrderDAO.getSaleWithMinOrder();
                System.out.println(saleId);
                shopOrderDAO.insertSaleAssignment(orderID, saleId);
                session.removeAttribute("temporaryAddress");
                session.removeAttribute("PayType");
                request.setAttribute("OrderMessage", "Order Placed");
                request.getRequestDispatcher("thanks.jsp").forward(request, response);
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
