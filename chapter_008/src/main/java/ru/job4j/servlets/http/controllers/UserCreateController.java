package ru.job4j.servlets.http.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.http.logic.DispatchFunction;
import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.logic.ValidateService;
import ru.job4j.servlets.http.persistent.Validate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserCreateController show form for create user.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 5.
 * @since 28.08.2019.
 */
public class UserCreateController extends HttpServlet {

    /**
     * Instance of singleton.
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Instance of singleton.
     */
    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(req.getReader().readLine(), User.class);
        dispatchFunction.actionChecker("add", user);
    }
}