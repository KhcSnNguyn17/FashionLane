package controller.user;

import java.io.*;

import entity.Cart;
import entity.CartItem;
import entity.Product;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.CartDAO;
import model.CartItemDAO;
import entity.User;
import java.util.List;

@WebServlet(name = "adjustQuantity", urlPatterns = {"/adjustQuantity"})

public class AdjustQuantity extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        CartItemDAO cid = new CartItemDAO();
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("VariationID");
        String choice = request.getParameter("choice");
        CartItemDAO cartItemDAO = new CartItemDAO();
        CartDAO cd = new CartDAO();
        User currUser = (User) request.getSession().getAttribute("acc");
        String buyerID = String.valueOf(currUser.getUserID());
        Cart curCart = cd.getCart(buyerID);
        if (curCart == null) {
            session.setAttribute("currentCart", curCart);
            if (choice.equals("plus")) {
                cid.plusQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
            } else {
                CartItem cartItem = cid.getCartQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                int quan = cartItem.getQuantity();
                if (quan > 1) {
                    cid.minusQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                }
            }
        } else {
            if (choice.equals("plus")) {
                cid.plusQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
            } else {
                CartItem cartItem = cid.getCartQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                int quan = cartItem.getQuantity();
                if (quan > 1) {
                    cid.minusQuantity(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                }
                else {
                    cid.deleteCartItem(xProductID, xVariationID, String.valueOf(curCart.getCartID()));
                }
            }
        }
        List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
        List<CartItem> cartItemList2 = cartItemDAO.getListCartItemByUserId(currUser.getUserID());
        session.setAttribute("cartItemList", cartItemList);
        session.setAttribute("cartItemList2", cartItemList2);
        response.sendRedirect(request.getHeader("referer"));
    }

}
