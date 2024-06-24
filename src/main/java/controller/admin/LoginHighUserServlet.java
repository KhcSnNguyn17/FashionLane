package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import entity.User;
import model.UserDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginHighUserServlet", urlPatterns = {"/loginHighUserServlet"})
public class LoginHighUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie arr[] = req.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("email")) {
                    req.setAttribute("email", o.getValue());
                }
                if (o.getName().equals("password")) {
                    req.setAttribute("password", o.getValue());
                }
            }
        }
        req.getRequestDispatcher("loginHighUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        UserDAO u = new UserDAO();
        User checkUser = null;
        try {
            checkUser = u.checkUser(email, (password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        User Role = u.getRoleByEmail(email);
        if (checkUser == null) {
            if (!email.isEmpty() || !password.isEmpty()) {
                req.setAttribute("error", "Email or Password cannot be empty!!!");
            }
            if (!email.isEmpty() && !password.isEmpty()) {
                req.setAttribute("error", "Email or Password is incorrect or not exist!!!");
            }
            req.getRequestDispatcher("loginHighUser.jsp").forward(req, resp);
        } else {
            if (Role.getRole() == 1 || Role.getRole() == 2 || Role.getRole() == 3) {
                HttpSession session = req.getSession();
                session.setAttribute("accHU", checkUser);
                //lưu cookie
                Cookie cookieAcc = new Cookie("email", email);
                Cookie cookiePass = new Cookie("password", password);
                cookieAcc.setMaxAge(60 * 60 * 24 * 365);
                if (req.getParameter("remember") != null) {
                    cookiePass.setMaxAge(60 * 60 * 24 * 365);
                } else {
                    cookiePass.setMaxAge(0);
                }
                resp.addCookie(cookieAcc); // lưu cookie lên client
                resp.addCookie(cookiePass); // lưu cookie lên client
                UserDAO ud = new UserDAO();
                List<User> userList = ud.getAllUser();
                req.setAttribute("userList", userList);
                User user = (User) session.getAttribute("accHU");
                req.setAttribute("u", user);
                req.getRequestDispatcher("dashboardManager.jsp").forward(req, resp);

            } else {
                req.setAttribute("error", "Email or Password is incorrect or not exist!!!");
                req.getRequestDispatcher("loginHighUser.jsp").forward(req, resp);
            }
        }
    }
}
