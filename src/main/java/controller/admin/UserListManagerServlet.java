package controller.admin;

import entity.Category;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListManagerServlet", value = "/UserListManagerServlet")
public class UserListManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("accHU");
        if (user == null) {
            response.sendRedirect("loginHighUserServlet");
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        UserDAO ud = new UserDAO();
        String role = request.getParameter("role");
        List<User> userList = ud.getUsersByRoleAndSort(role);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userListManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xRole = request.getParameter("role").trim();
        String xName = request.getParameter("userName").trim();
        UserDAO ud = new UserDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Category> cateList = cd.getAllCategory();
        request.setAttribute("cateList", cateList);
        List<User> userList = ud.searchByName(xName, xRole);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userListManager.jsp").forward(request, response);
    }
}
