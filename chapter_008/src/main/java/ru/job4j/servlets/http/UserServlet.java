package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class UserServlet creates servlet for List of users fillings.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 21.08.2019.
 */
public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";

    /**
     * Map of IF-ELSE logic.
     */
    private final Map<String, Function<User, String>> functions = new HashMap<>();

    /**
     * Constructor init dispatch map.
     */
    public UserServlet() {
        initDispatch();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        this.logic.findAll().forEach(user -> writer.println(user.toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = getStringParameters(req, "action");
        String id = getStringParameters(req, "id");
        String name = getStringParameters(req, "name");
        String login = getStringParameters(req, "login");
        String email = getStringParameters(req, "email");

        String apply = this.functions.getOrDefault(action, user -> "action not found")
                .apply(new User(Integer.parseInt(id), name, login, email));
        PrintWriter writer = resp.getWriter();
        writer.println(apply);
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }

    /**
     * Fill map of logic.
     */
    private void initDispatch() {
        functions.put(ADD, this.add);
        functions.put(UPDATE, this.update);
        functions.put(DELETE, this.delete);
    }

    /**
     * ADD
     */
    private Function<User, String> add = (user -> {
        User result = logic.add(user);
        return result != null ? result.toString() : "Such User already exists";
    });

    /**
     * UPDATE
     */
    private Function<User, String> update = (user ->
            logic.update(user) ? "User was update" : "User wasn't update");

    /**
     * DELETE
     */
    private Function<User, String> delete = (user ->
            logic.delete(user) ? "User was delete" : "User wasn't delete");
}