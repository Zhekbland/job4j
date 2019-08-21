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
 * @version 1.
 * @since 21.08.2019.
 */
public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User user : this.logic.findAll()) {
            writer.println(user.toString());
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (action.equals("add")) {
            logic.add(new User(id, name, login, email));
        } else if (req.getParameter("action").equals("delete")) {
            logic.delete(new User(id, name, login, email));
        } else if (req.getParameter("action").equals("update")) {
            logic.update(new User(id, name, login, email));
        }
        doGet(req, resp);
    }
}
