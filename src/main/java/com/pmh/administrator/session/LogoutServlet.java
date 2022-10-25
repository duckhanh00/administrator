package com.pmh.administrator.session;

import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws SecurityException, IOException {
        servletResponse.setContentType("text/html");
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }

        HttpSession session = servletRequest.getSession(false);
        System.out.println("User="+session.getAttribute("user"));
        if(session != null){
            session.invalidate();
        }
        servletResponse.sendRedirect("login.html");
    }
}
