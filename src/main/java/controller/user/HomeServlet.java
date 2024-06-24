package controller.user;

import entity.CartItem;
import entity.Product;
import java.io.*;

import entity.Category;
import entity.Story;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import entity.Collection;
import entity.User;

import java.util.*;

@MultipartConfig
@WebServlet(name = "category-servlet", value = "/home-servlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CategoryDAO c = new CategoryDAO();
        CollectionDAO col = new CollectionDAO();
        StoryDAO storyDAO = new StoryDAO();
        List<Collection> listCollection = col.getAllCollections();
        List<Product> plist = new ArrayList<>();

        List<Story> storyList = storyDAO.getAllStory("all");
        List<Category> cateList = c.getAllCategory();
        CartItemDAO cartItemDAO = new CartItemDAO();
        HttpSession session = request.getSession();
        User currUser = (User) request.getSession().getAttribute("acc");
        if (currUser != null) {
            List<Product> cartItemList = cartItemDAO.getUserItem(currUser.getUserID());
            List<CartItem> cartItemList2 = cartItemDAO.getListCartItemByUserId(currUser.getUserID());
            session.setAttribute("cartItemList", cartItemList);
            session.setAttribute("cartItemList2", cartItemList2);
        }

        request.setAttribute("storyList", storyList);
        request.setAttribute("cateList", cateList);
        request.setAttribute("plist", plist);
        request.setAttribute("listCollection", listCollection);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
