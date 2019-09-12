package ru.job4j.servlets.http.controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthFilter filters controllers.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 28.08.2019.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().contains("/signin")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
                return;
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
