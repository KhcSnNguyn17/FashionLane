package controller.user;

import entity.Product;
import java.io.*;

import entity.Category;
import entity.Color;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

@WebServlet(name = "productList-servlet", value = "/productList-servlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        ColorDAO col = new ColorDAO();

        String cateID = request.getParameter("categoryID").trim();
        String colID = request.getParameter("color_ID").trim();
        String collectionID = request.getParameter("collectionID");

        if (collectionID == null) {
            List<Category> cateList = cd.getAllCategory();
            Category category = cd.getCategory(cateID);
            request.setAttribute("cateList", cateList);
            request.setAttribute("category", category);

            if (colID.equals("all")) {
                List<Product> productList = pd.getProductsByCID(cateID);
                request.setAttribute("productList", productList);
            } else {
                List<Product> productList = pd.getProductsByColIDCateID(cateID, colID);
                request.setAttribute("productList", productList);
            }
            List<Color> colorList = col.getAllColors();
            request.setAttribute("colorList", colorList);

        } else {
            List<Category> cateList = cd.getAllCategory();
            request.setAttribute("cateList", cateList);
            if (colID.equals("all")) {
                Category category = cd.getCategory(cateID);
                request.setAttribute("category", category);
                List<Product> productList = pd.getProductsByCollectIDCateID(collectionID, cateID);
                request.setAttribute("productList", productList);
            } else {
                Category category = cd.getCategory(cateID);
                request.setAttribute("category", category);
                List<Product> productList = pd.getProductsByColIDCollectID(cateID, colID, collectionID);
                request.setAttribute("productList", productList);
            }
            List<Color> colorList = col.getAllColors();
            request.setAttribute("colorList", colorList);
            request.setAttribute("collectionID", collectionID);
        }
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
