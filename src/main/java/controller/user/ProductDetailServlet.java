package controller.user;

import entity.Product;
import java.io.*;

import entity.Color;
import entity.Size;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

@WebServlet(name = "productDetail-servlet", urlPatterns = {"/productDetail-servlet"})

public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xId = request.getParameter("ProductID").trim();
        String xColor_Name = request.getParameter("color_Name").trim();
        ProductDAO pd = new ProductDAO();
        ColorDAO col = new ColorDAO();
        SizeDAO si = new SizeDAO();
        Product x = pd.getProductByProIDColName(xId, xColor_Name);
        List<Color> colors = col.getColorsByProductID(xId);
        List<Size> sizes = si.getSizesOfThatProduct(xId, xColor_Name);

        request.setAttribute("pi", x);
        request.setAttribute("colors", colors);
        request.setAttribute("sizes", sizes);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xId = request.getParameter("ProductID").trim();
        String xColor_Name = request.getParameter("color_Name").trim();
        String xSize_Name = request.getParameter("size_name").trim();
        ProductDAO pd = new ProductDAO();
        ColorDAO col = new ColorDAO();
        SizeDAO si = new SizeDAO();
        Product x = pd.getProductByProIDColNameSizName(xId, xColor_Name, xSize_Name);
        List<Color> colors = col.getColorsByProductID(xId);
        List<Size> sizes = si.getSizesOfThatProduct(xId, xColor_Name);
        request.setAttribute("pi", x);
        request.setAttribute("colors", colors);
        request.setAttribute("sizes", sizes);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
    }
}
