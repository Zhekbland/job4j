package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UsersServlet show all users in table
 * and create button for create, delete, update.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 28.08.2019.
 */
public class UsersServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    public void init() {
        this.validateService.add(new User(0, "name1", "login1", "email1"));
        this.validateService.add(new User(0, "name2", "login2", "email2"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = getStringParameters(req, "action");
        String id = getStringParameters(req, "id");
        String name = getStringParameters(req, "name");
        String login = getStringParameters(req, "login");
        String email = getStringParameters(req, "email");

        String apply = dispatchFunction.actionChecker(action,
                new User(Integer.parseInt(id), name, login, email));

        PrintWriter writer = resp.getWriter();
        writer.println(apply);
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
        writer.close();
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }
}