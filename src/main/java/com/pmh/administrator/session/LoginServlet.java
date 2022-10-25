package com.pmh.administrator.session;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "password";

    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String user = servletRequest.getParameter("user");
        String pwd = servletRequest.getParameter("pwd");

        if(userID.equals(user) && password.equals(pwd)) {
            HttpSession session = servletRequest.getSession();
            session.setAttribute("user", "Khanh");
            session.setMaxInactiveInterval(100);
            Cookie username = new Cookie("user", user);
            username.setMaxAge(100);
            servletResponse.addCookie(username);
            servletResponse.sendRedirect("LoginSuccess.jsp");
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("login.html");
            PrintWriter out = servletResponse.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(servletRequest, servletResponse);
        }
    }
}
