package controller.user;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;

@WebServlet(name = "user-account-detail-servlet", urlPatterns = {"/user-account-detail-servlet"})
public class UserAccountDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO u = new UserDAO();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("acc");
        user = u.getUserById(user.getUserID());
        req.setAttribute("u", user);
        req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
    }

}
