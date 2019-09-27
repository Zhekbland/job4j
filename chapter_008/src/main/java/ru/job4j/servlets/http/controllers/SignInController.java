package ru.job4j.servlets.http.controllers;

import ru.job4j.servlets.http.logic.ValidateService;
import ru.job4j.servlets.http.persistent.Validate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class SignInController does signIn.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 5.
 * @since 28.08.2019.
 */
public class SignInController extends HttpServlet {

    /**
     * Instance of singleton.
     */
    private final Validate validateService = ValidateService.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (validateService.isCredentional(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect(String.format("%s/users", request.getContextPath()));
        } else {
            request.setAttribute("error", "Credentional invalid");
            doGet(request, response);
        }
    }
}
