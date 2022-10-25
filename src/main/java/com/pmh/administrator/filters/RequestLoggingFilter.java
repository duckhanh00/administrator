package com.pmh.administrator.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context =  fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Enumeration<String> params = req.getParameterNames();

        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = servletRequest.getParameter(name);
            this.context.log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                this.context.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("End RequestLoggingFilter!!!!!!!!!!!!!!!!!!!1");
    }
}
