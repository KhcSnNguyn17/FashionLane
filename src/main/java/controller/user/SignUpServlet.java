package controller.user;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDAO;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet(name = "register-servlet", urlPatterns = {"/register-servlet"})
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String repass = req.getParameter("repass").trim();
        Date date = Date.valueOf(req.getParameter("dob").trim());
        int role = Integer.parseInt(req.getParameter("role").trim());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
        UserDAO u = new UserDAO();
        if (email.isEmpty() || password.isEmpty() || repass.isEmpty() || username.isEmpty() ) {
            req.setAttribute("error", "Please fill all the fields");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else if(!username.matches("^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")){
            req.setAttribute("error", "Username must be 5-20 letters, no _ or . ,");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (password.length() < 3 || password.length() > 20) {
            req.setAttribute("error", "Password must be between 3 and 20 characters");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (!password.equals(repass)) {
            req.setAttribute("error", "Password and repassword are not the same");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (!calendar.getTime().after(date)) {
            req.setAttribute("error", "User must be 18 or older");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            boolean checkAccountExist = u.checkAccountExist(username, email);
            if (checkAccountExist) {
                req.setAttribute("error", "Account already exists");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                try {
                    u.addUser(username , password ,email, role, date);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.setAttribute("newemail", email);
                req.setAttribute("newpass", password);
                req.setAttribute("success", "Register successfully please sign in");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}
