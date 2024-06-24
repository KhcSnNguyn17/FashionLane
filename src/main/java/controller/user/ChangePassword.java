package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.User;
import model.UserDAO;

import java.io.IOException;

@WebServlet(name = "changePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mod = req.getParameter("mod");
        if (mod == null) {
            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
        } else if (mod.equals("2")) {
            req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
        } else {
            if (mod.equals("1")) {
                HttpSession session = req.getSession();
                UserDAO u = new UserDAO();
                User user = (User) session.getAttribute("acc");
                user = u.getUserById(user.getUserID());
                req.setAttribute("u", user);
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mod = req.getParameter("mod");
        String oldPassword = req.getParameter("oldPassword").trim();
        String newPassword = req.getParameter("newPassword").trim();
        String confirmPassword = req.getParameter("confirmPassword").trim();
        HttpSession session = req.getSession();
        UserDAO u = new UserDAO();
        User user = (User) session.getAttribute("acc");
        if(user == null) {
            resp.sendRedirect("login-servlet");
            return;
        }
        if (mod == null) {
            User userGet = u.getUserById(user.getUserID());
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                req.setAttribute("error", "Please fill all the fields");
                req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
            } else if (newPassword.length() < 3 || newPassword.length() > 20) {
                req.setAttribute("error", "Password must be between 3 and 20 characters");
                req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
            } else if (confirmPassword.length() < 3 || confirmPassword.length() > 20) {
                req.setAttribute("error", "Password must be between 3 and 20 characters");
                req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
            } else if (!newPassword.equals(confirmPassword)) {
                req.setAttribute("error", "New password and confirm password are not the same");
                req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
            } else {
                try {
                    if (!oldPassword.equals(userGet.getPassword())) {
                        req.setAttribute("error", "Old password is not correct");
                        req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
                    } else {
                        try {
                            u.ChangePass((newPassword), (user.getUserID()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        req.setAttribute("success", "Changing password successfully");
                        req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (mod.equals("2")) {
                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    req.setAttribute("error", "Please fill all the fields");
                    req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                } else if (newPassword.length() < 3 || newPassword.length() > 20) {
                    req.setAttribute("error", "Password must be between 3 and 20 characters");
                    req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                } else if (confirmPassword.length() < 3 || confirmPassword.length() > 20) {
                    req.setAttribute("error", "Password must be between 3 and 20 characters");
                    req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                } else if (!newPassword.equals(confirmPassword)) {
                    req.setAttribute("error", "New password and confirm password are not the same");
                    req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                } else {
                    try {
                        if (!oldPassword.equals(user.getPassword())) {
                            req.setAttribute("error", "Old password is not correct");
                            req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                        } else {
                            try {
                                u.ChangePass((newPassword), (user.getUserID()));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            req.setAttribute("success", "Changing password successfully");
                            req.getRequestDispatcher("changePasswordHighUser.jsp").forward(req, resp);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}
