package controller.user;

import java.io.*;
import java.util.*;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

// Define the servlet with URL pattern /addToCart
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

    // Define constants for content type, session attributes, request parameters, and messages
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String ATTR_ACC = "acc";
    private static final String ATTR_CURRENT_CART = "currentCart";
    private static final String ATTR_CART_ITEM_LIST = "cartItemList";
    private static final String ATTR_CART_ITEM_LIST_2 = "cartItemList2";
    private static final String PARAM_PRODUCT_ID = "ProductID";
    private static final String PARAM_COLOR_NAME = "color_Name";
    private static final String PARAM_SIZE_NAME = "size_name";
    private static final String CART_ERROR_LOGIN = "Please Login to perform this action!";
    private static final String CART_ERROR_EXIST = "Product has already been in cart!!!";
    private static final String CART_SUCCESS = "Product has been added to cart!!!";

    /**
     * Handles the HTTP GET method.
     * Sets the response content type to text/html with UTF-8 encoding.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type
        response.setContentType(CONTENT_TYPE);
    }

    /**
     * Handles the HTTP POST method.
     * Adds a product to the user's cart if the user is logged in, otherwise prompts for login.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type
        response.setContentType(CONTENT_TYPE);

        // Get the current session and user from the session attribute
        HttpSession session = request.getSession();
        User currUser = (User) session.getAttribute(ATTR_ACC);

        // Check if the user is logged in
        if (currUser == null) {
            // Redirect to product detail page with login error message
            redirectToProductDetailWithError(request, response, CART_ERROR_LOGIN);
            return;
        }

        try {
            // Retrieve product ID, color name, and size name from the request
            String xProductID = request.getParameter(PARAM_PRODUCT_ID);
            String xColorName = request.getParameter(PARAM_COLOR_NAME).trim();
            String xSizeName = request.getParameter(PARAM_SIZE_NAME).trim();

            // Get the variation for the product
            Variation xVariation = getVariation(xProductID, xColorName, xSizeName);
            if (xVariation == null) {
                // Redirect to product detail page with invalid variation error message
                redirectToProductDetailWithError(request, response, "Invalid product variation.");
                return;
            }

            // Handle adding the product to the cart
            handleAddToCart(request, response, session, currUser, xProductID, xVariation);
        } catch (Exception e) {
            // Print stack trace and redirect to product detail page with a generic error message
            e.printStackTrace();
            redirectToProductDetailWithError(request, response, "An error occurred while adding to cart.");
        }
    }

    /**
     * Retrieves the variation for the given product ID, color, and size.
     *
     * @param productID the product ID
     * @param colorName the color name
     * @param sizeName  the size name
     * @return the Variation object, or null if not found
     */
    private Variation getVariation(String productID, String colorName, String sizeName) {
        // Create a VariationDAO object to retrieve the variation
        VariationDAO vd = new VariationDAO();
        return vd.getVariation(productID, colorName, sizeName);
    }

    /**
     * Handles adding a product to the cart.
     *
     * @param request   the HttpServletRequest object
     * @param response  the HttpServletResponse object
     * @param session   the HttpSession object
     * @param currUser  the current User object
     * @param productID the product ID
     * @param variation the Variation object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, User currUser, String productID, Variation variation)
            throws ServletException, IOException {
        // Create a CartItemDAO object to interact with cart items
        CartItemDAO cartItemDAO = new CartItemDAO();
        String buyerID = String.valueOf(currUser.getUserID());

        // Check if the product is already in the cart
        if (cartItemDAO.checkCartItemExist(productID, String.valueOf(variation.getVariationID()), buyerID)) {
            // Redirect to product detail page with product exists error message
            redirectToProductDetailWithError(request, response, CART_ERROR_EXIST);
        } else {
            // Add the product to the cart and redirect back to the referrer
            addProductToCart(session, currUser, productID, variation);
            response.sendRedirect(request.getHeader("referer"));
        }
    }

    /**
     * Adds the specified product and variation to the user's cart.
     *
     * @param session   the HttpSession object
     * @param currUser  the current User object
     * @param productID the product ID
     * @param variation the Variation object
     */
    private void addProductToCart(HttpSession session, User currUser, String productID, Variation variation) {
        // Create DAO objects for Cart and CartItem
        CartDAO cd = new CartDAO();
        CartItemDAO cartItemDAO = new CartItemDAO();
        String buyerID = String.valueOf(currUser.getUserID());

        // Retrieve the current cart from the session or create a new one
        Cart currentCart = (Cart) session.getAttribute(ATTR_CURRENT_CART);
        if (currentCart == null) {
            // Get user cart items and insert a new cart if empty
            List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
            if (cartItemList.isEmpty()) {
                cd.insertProductIntoCart(buyerID);
            }
        }

        // Retrieve the updated cart and set it in the session
        Cart curCart = cd.getCart(buyerID);
        session.setAttribute(ATTR_CURRENT_CART, curCart);

        // Insert the product into the cart
        cartItemDAO.insert(String.valueOf(curCart.getCartID()), productID, String.valueOf(variation.getVariationID()));

        // Update the session with the user's cart items
        updateSessionCartItems(session, currUser);
    }

    /**
     * Updates the session with the current user's cart items.
     *
     * @param session  the HttpSession object
     * @param currUser the current User object
     */
    private void updateSessionCartItems(HttpSession session, User currUser) {
        // Create a CartItemDAO object to retrieve cart items
        CartItemDAO cartItemDAO = new CartItemDAO();
        
        // Get the user's cart items and update the session attributes
        List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
        List<CartItem> cartItemList2 = cartItemDAO.getListCartItemByUserId(currUser.getUserID());
        session.setAttribute(ATTR_CART_ITEM_LIST, cartItemList);
        session.setAttribute(ATTR_CART_ITEM_LIST_2, cartItemList2);
    }

    /**
     * Redirects to the product detail page with an error message.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @param errorMsg the error message to display
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void redirectToProductDetailWithError(HttpServletRequest request, HttpServletResponse response, String errorMsg)
            throws ServletException, IOException {
        // Set the error message attribute and forward to the product detail servlet
        request.setAttribute("CartMessError", errorMsg);
        request.getRequestDispatcher("productDetail-servlet").forward(request, response);
    }
}
