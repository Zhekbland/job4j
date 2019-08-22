package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UserServlet creates servlet for List of users fillings.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 21.08.2019.
 */
public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        this.logic.findAll().forEach(x -> writer.println(x.toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (ADD.equals(action)) {
            logic.add(new User(id, name, login, email));
        } else if (DELETE.equals(action)) {
            logic.delete(new User(id, name, login, email));
        } else if (UPDATE.equals(action)) {
            logic.update(new User(id, name, login, email));
        }
        doGet(req, resp);
    }
}
