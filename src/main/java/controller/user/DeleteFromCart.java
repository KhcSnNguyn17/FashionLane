package controller.user;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import entity.*;
import java.util.List;

@WebServlet(name = "DeleteFromCart", urlPatterns = {"/DeleteFromCart"})
public class DeleteFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartItemDAO cid = new CartItemDAO();
        HttpSession session = request.getSession();
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("variationID");
        CartItemDAO cartItemDAO = new CartItemDAO();
        CartDAO cd = new CartDAO();
        Cart curCart;
        Cart currentCart = (Cart) request.getSession().getAttribute("currentCart");
        User currUser = (User) request.getSession().getAttribute("acc");
        String buyerID = String.valueOf(currUser.getUserID());
        if (currentCart == null) {
            curCart = cd.getCart(buyerID);
            session.setAttribute("currentCart", curCart);
            cid.deleteCartItem(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
        } else {
            cid.deleteCartItem(xProductID, xVariationID, String.valueOf(currentCart.getCartID()));
        }
        List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
        List<CartItem> cartItemList2 = cartItemDAO.getListCartItemByUserId(currUser.getUserID());
        session.setAttribute("cartItemList", cartItemList);
        session.setAttribute("cartItemList2", cartItemList2);

        response.sendRedirect(request.getHeader("referer"));
    }
}
