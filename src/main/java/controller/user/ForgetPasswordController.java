/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import Utils.Email;
import Utils.Generate;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDAO;

/**
 *
 * @author HP
 */
public class ForgetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forget-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        UserDAO userDao = new UserDAO();
        User userForget = userDao.getUserForget(username, email);
        if (userForget != null) {
            Email emailSend = new Email();
            Generate generate = new Generate();
            String newPassword = generate.generatePassword(9);
            String messageSend = "Your new password is: " + newPassword + ". Please login with new password";
            boolean isSend = emailSend.sendEmail(email, "Reset password", messageSend, null);
            if (isSend) {
                userDao.ChangePass(newPassword, userForget.getUserID());
                request.setAttribute("messageSuccess", "Your password was reset.");
                request.getRequestDispatcher("forget-password.jsp").forward(request, response);
            } else {
                request.setAttribute("messageError", "Reset password fail. Try again.");
                request.getRequestDispatcher("forget-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("messageError", "Your account can not found.");
            request.getRequestDispatcher("forget-password.jsp").forward(request, response);
        }
    }


}
