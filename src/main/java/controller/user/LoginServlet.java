package controller.user;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;

@WebServlet(name = "login-servlet", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {

    private boolean checkCookies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        String email = null;
        String password = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("emailUser".equals(cookie.getName())) {
                    email = cookie.getValue();
                } else if ("passwordUser".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        if (email != null && password != null) {
            UserDAO userDAO = new UserDAO();
            User checkUser = userDAO.checkUser(email, password);
            if (checkUser != null) {
                HttpSession session = req.getSession();
                session.setAttribute("acc", checkUser);
                resp.sendRedirect("home-servlet");
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkCookies(req, resp)) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkCookies(req, resp)) {
            return;
        }

        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        UserDAO u = new UserDAO();

        User checkUser = u.checkUser(email, password);
        if (checkUser == null) {
            if (email.isEmpty()) {
                req.setAttribute("EmailErr", "Email not allow empty!!!");
            }
            if (password.isEmpty()) {
                req.setAttribute("PassErr", "Password not allow empty!!!");
            }
            if (!email.isEmpty() && !password.isEmpty()) {
                req.setAttribute("Message", "Email or Password is incorrect or not exist!!!");
            }
            if (password.length() < 3 || password.length() > 20) {
                req.setAttribute("PassErr", "Password must be from 3 to 20 characters!!!");
            }
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("acc", checkUser);

            Cookie cookieAcc = new Cookie("emailUser", email);
            Cookie cookiePass = new Cookie("passwordUser", password);
            cookieAcc.setMaxAge(60 * 60 * 24 * 365);
            if (req.getParameter("remember me") != null) {
                cookiePass.setMaxAge(60 * 60 * 24 * 365);
            } else {
                cookiePass.setMaxAge(0);
            }
            resp.addCookie(cookieAcc);
            resp.addCookie(cookiePass);
            switch (checkUser.getRole()) {
                //admin: 1
                case 1:
                    req.setAttribute("Message", "Your account can not login here");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    break;
                //sale: 2
                case 2:
                    resp.sendRedirect("saler");
                    break;
                //makerting: 3
                case 3:
                    //TODO
                    break;
                //user: 4
                case 4:
                    //TODO
                    resp.sendRedirect("home-servlet");
                    break;
                //sale manager: 5
                case 5:
                    resp.sendRedirect("saleManager");
                    break;
                //staff: 6
                case 6:
                    //TODO
                    break;
                default:
                    resp.sendRedirect("home-servlet");
            }
        }
    }
}
